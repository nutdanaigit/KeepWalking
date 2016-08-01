package com.augmentis.ayp.keepwalking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Nutdanai on 7/27/2016.
 */
public class KeepWalkFragment extends Fragment {
    private static final String CRIME_ID = "CrimeFragment.CRIME_ID";
    private static final String CRIME_POSITION = "CrimeFragment.CRIME_POS";
    private KeepWalk keep;
    private int position;
    private EditText editText;

    public KeepWalkFragment(){}

    public static KeepWalkFragment newInstance(UUID crimeId, int position){
        Bundle args = new Bundle();
        args.putSerializable(CRIME_ID,crimeId);
        args.putInt(CRIME_POSITION,position);
            KeepWalkFragment keepFragment = new KeepWalkFragment();
            keepFragment.setArguments(args);
            return  keepFragment;

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId =(UUID) getArguments().getSerializable(CRIME_ID);
        position  = getArguments().getInt(CRIME_POSITION);
        keep = KeepWalkLab.getInstance(getActivity()).getCrimeById(crimeId) ;
//        Log.d(CrimeListFragment.TAG,"crime.getId()=" + crime.getId());
        Log.d(KeepWalkListFragment.TAG,"crime.getTitle()=" + keep.getTitle());
    }

        private Button saveBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_keepwalk,container,false);
        editText = (EditText) v. findViewById(R.id.keepwalk_title);
        editText.setText(keep.getTitle());
        saveBtn = (Button) v.findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent saveBtn = new Intent(getActivity(),KeepWalkListActivity.class);
                keep.setTitle(editText.getText().toString());
                addThisPositionToResult(position);
                keep.setKeepDate(new Date());
                startActivity(saveBtn);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int cout, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });
//        keepDateButton = (Button) v.findViewById(R.id.);
//        keepDateButton.setText( getFormattedDate(crime.getCrimeDate()));
//        keepDateButton.setEnabled(false);


        return v;
    }



    private String  getFormattedDate(Date date){
        return new SimpleDateFormat("dd MMMM yyyy").format(date);
    }

    private void addThisPositionToResult(int position){
        if (getActivity() instanceof  KeepWalkPagerActivity){
            ((KeepWalkPagerActivity) getActivity()).addPageUpdate(position);
        }
    }


}
