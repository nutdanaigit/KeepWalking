package com.augmentis.ayp.keepwalking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
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

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder dialog  = new AlertDialog.Builder(this);
        dialog.setTitle("Do you want to Exit program ?");
        dialog.setMessage("คุณต้องการออกจากโปรแกรมหรือไม่");

        dialog.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                System.exit(0);

            }
        });
        dialog.setNeutralButton("ไม่", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        dialog.show();


    }


}
