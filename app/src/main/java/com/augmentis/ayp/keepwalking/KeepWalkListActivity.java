package com.augmentis.ayp.keepwalking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

public class KeepWalkListActivity extends FragmentActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fragment_container);


        if(f == null){
            f = new KeepWalkListFragment();
            fm.beginTransaction().add(R.id.fragment_container,f).commit();
        }else{
            //
        }


    }
}
