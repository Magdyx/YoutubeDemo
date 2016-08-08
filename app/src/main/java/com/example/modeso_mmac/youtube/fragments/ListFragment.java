package com.example.modeso_mmac.youtube.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.modeso_mmac.youtube.R;
import com.example.modeso_mmac.youtube.adapters.VideosListRecyclerAdapter;
import com.example.modeso_mmac.youtube.listeners.VideosListListener;
import com.example.modeso_mmac.youtube.models.Video;
import com.example.modeso_mmac.youtube.utils.Constants;
import com.example.modeso_mmac.youtube.viewmodels.VideosListViewModel;

import java.util.List;

/**
 * Created by Belal Mohamed on 7/27/16.
 * www.modeso.ch
 */
public class ListFragment extends Fragment implements VideosListListener {

    private RecyclerView mVideosList;
    private EditText mSearchEditText;
    private Button mSearchButton;
    private ProgressBar mProgressBar;
    private VideosListViewModel mViewModel;
    private VideosListRecyclerAdapter mAdapter;
    private boolean mShowProgressBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mViewModel = new VideosListViewModel(this, context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mViewModel != null) {
            mViewModel.onCreate();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        mVideosList = (RecyclerView) rootView.findViewById(R.id.videos_list);
        mSearchEditText = (EditText) rootView.findViewById(R.id.search_query_edittext);
        mSearchButton = (Button) rootView.findViewById(R.id.search_button);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        mShowProgressBar = savedInstanceState != null && savedInstanceState.getBoolean(Constants.SHOW_PROGRESS_DIALOG_KEY);
        initRecyclerView();
        initSearchButton();
        checkAlreadyExistingData();
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(Constants.SHOW_PROGRESS_DIALOG_KEY, mShowProgressBar);
    }

    private void initSearchButton() {
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchQuery = mSearchEditText.getText().toString();
                if (searchQuery.trim().length() > 0) {
                    if (mViewModel != null) {
                        mAdapter.clearList();
                        mShowProgressBar = true;
                        mProgressBar.setVisibility(View.VISIBLE);
                        mViewModel.requestVideosList(searchQuery);
                    }
                } else {
                    Toast.makeText(getContext(), getContext().getString(R.string.empty_field_message), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new VideosListRecyclerAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mVideosList.setLayoutManager(linearLayoutManager);
        mVideosList.setAdapter(mAdapter);
    }

    private void checkAlreadyExistingData() {
        mSearchEditText.setText(mViewModel.getCurrentSearchQuery());
        if (mShowProgressBar) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mViewModel != null) {
            mViewModel.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mViewModel != null) {
            mViewModel.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.onDestroy();
        }
    }

    @Override
    public void onListReady(List<Video> videos) {
        mProgressBar.setVisibility(View.GONE);
        mShowProgressBar = false;
        mAdapter.updateAdapter(videos);
    }

    @Override
    public void onError(String message) {
        mProgressBar.setVisibility(View.GONE);
        mShowProgressBar = false;
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
