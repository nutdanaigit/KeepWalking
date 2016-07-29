package com.augmentis.ayp.keepwalking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Nutdanai on 7/27/2016.
 */
public class KeepWalkPagerActivity extends FragmentActivity {
    private ViewPager _viewPager;
    private List<KeepWalk> _keepp;
    private int _position;
    private UUID _keepId;
    private List<Integer> positionChanged = new ArrayList<>();

    protected static final String CRIME_ID = "crimeActivity.crimeId";
    protected static final String CRIME_POSITION = "crimeActivity.crimePos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keepwalk_pager);
        if(getIntent().getExtras()!=null){
            Log.d("pearl","testtttt");
            _keepId = (UUID) getIntent().getSerializableExtra(CRIME_ID);
            _position = (int) getIntent().getExtras().get(CRIME_POSITION);
        }

        Log.d("pearl","testtttt2123");
        _viewPager = (ViewPager) findViewById(R.id.activity_keepwalk_pager_view_pager);

        _keepp = KeepWalkLab.getInstance(this).getKeepList();


        FragmentManager fm = getSupportFragmentManager();

        _viewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                    KeepWalk crime = _keepp.get(position);
                    Fragment f = KeepWalkFragment.newInstance(crime.getId(), position);

                    return f;

            }

            @Override
            public int getCount() {

                return _keepp.size();
            }
        });
        //set position
        int position = KeepWalkLab.getInstance(this).getCrimePositionById(_keepId);
        _viewPager.setCurrentItem(position);


    }

    protected void addPageUpdate(int position) {
        if (positionChanged.contains(position)) {
            return;
        }
        positionChanged.add(position);

        Intent intent = new Intent();
        Integer[] positions = positionChanged.toArray(new Integer[0]);
        intent.putExtra("position", positions);
        Log.d(KeepWalkListFragment.TAG, "send position back: " + position);
        setResult(Activity.RESULT_OK, intent);
    }


    public static Intent newIntent(Context activity, UUID id, int position) {
        Intent intent = new Intent(activity, KeepWalkPagerActivity.class);
        intent.putExtra(CRIME_ID, id);
        intent.putExtra(CRIME_POSITION, position);
        Log.d("pearl","Show"+id +"  "+position);
        return intent;
    }
}
