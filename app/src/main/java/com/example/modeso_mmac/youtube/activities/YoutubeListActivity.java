package com.example.modeso_mmac.youtube.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.modeso_mmac.youtube.fragments.ListFragment;
import com.example.modeso_mmac.youtube.R;

/**
 * Created by Belal Mohamed on 7/27/16.
 * www.modeso.ch
 */
public class YoutubeListActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_list);

        mFragmentManager = getSupportFragmentManager();
        attachListFragment();
    }

    private void attachListFragment() {
        Fragment fragment = mFragmentManager.findFragmentByTag("LIST_FRAGMENT");

        if (fragment == null) {
            fragment = new ListFragment();
        }

        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, "LIST_FRAGMENT")
                .commit();
    }
}
