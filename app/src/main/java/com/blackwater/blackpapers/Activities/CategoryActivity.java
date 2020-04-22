package com.blackwater.blackpapers.Activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.blackwater.blackpapers.Common.Common;
import com.blackwater.blackpapers.Fragment.CategoryFragment;
import com.blackwater.blackpapers.Fragment.HomeFragment;
import com.blackwater.blackpapers.R;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.categories);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FrameLayout category = (FrameLayout)findViewById(R.id.frame_category);
        CategoryFragment categoryFragment = new  CategoryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_category, categoryFragment);
        transaction.commit();
    }
}
