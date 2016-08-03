package com.augmentis.ayp.keepwalking;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.augmentis.ayp.keepwalking.model.KeepWalk;
import com.augmentis.ayp.keepwalking.model.KeepWalkLab;

import java.util.Date;
import java.util.List;

/**
 * Created by Nutdanai on 8/1/2016.
 */
public class KeepWalkDialog extends DialogFragment {
        private EditText editTxt;
        private Button saveBtn;
        private KeepWalk keep;
        private List<KeepWalk> _keep;


    public static KeepWalkDialog newInstance() {
        KeepWalkDialog  kd = new KeepWalkDialog();
        Bundle args = new Bundle();
        kd.setArguments(args);
        return  kd;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_keepwalk,null);
        editTxt = (EditText) v.findViewById(R.id.keepwalk_title);
        saveBtn = (Button) v.findViewById(R.id.save_btn);
        saveBtn.setVisibility(View.GONE);

        _keep = KeepWalkLab.getInstance(getActivity()).getKeep();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setView(v);
        dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                keep = new KeepWalk();
                Log.d("OK",""+ editTxt.getText().toString());
                keep.setTitle(editTxt.getText().toString());
                keep.setKeepDate(new Date());
                _keep.add(keep);
                getTargetFragment().onActivityResult(80, Activity.RESULT_OK, intent);
            }
        });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        return dialog.create();

    }


}
