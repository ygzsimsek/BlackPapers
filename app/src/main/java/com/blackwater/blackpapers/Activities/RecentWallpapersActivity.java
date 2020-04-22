package com.blackwater.blackpapers.Activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.blackwater.blackpapers.Fragment.CategoryFragment;
import com.blackwater.blackpapers.Fragment.RecentFragment;
import com.blackwater.blackpapers.Fragment.TrendingFragment;
import com.blackwater.blackpapers.R;

public class RecentWallpapersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_wallpapers);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.popular_collections);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //TODO: CREATE NEW FRAGMENT THAT LISTS ALL WALLPAPERS
        FrameLayout wallpaper = (FrameLayout)findViewById(R.id.frame_wp);
        RecentFragment recentFragment = new RecentFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_wp, recentFragment);
        transaction.commit();
    }
}
