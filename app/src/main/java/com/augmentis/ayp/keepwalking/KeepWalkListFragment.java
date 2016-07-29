package com.augmentis.ayp.keepwalking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nutdanai on 7/27/2016.
 */
public class KeepWalkListFragment extends Fragment {
    private static final int REQUEST_UPDATED_KEEPWALK = 137;
    private RecyclerView _keepRecyclerView;
    private KeepAdapter _adapter;
    protected static final String TAG = "CRIME_LIST";
    private Integer[] keepPos;
    private Button add_btn;


    /**
     *  start
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_keepwalk_list, container,false); //create view to get in container
        // Inside RecyclerView,I don't know how it's work.
        _keepRecyclerView = (RecyclerView) v.findViewById(R.id.keepwalk_recycler_view); //Get RecyclerView and give v to find ID
        _keepRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        add_btn = (Button) v.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                KeepWalkLab keep = KeepWalkLab.getInstance(getActivity());
                keep.addKeep("");
//                Log.d(TAG," Test  "+keep.getKeepList().size());
                Intent addBtn =KeepWalkPagerActivity.newIntent(getActivity(),keep.getKeepList().get(keep.getKeepList().size()-1).getId(),keep.getKeepList().size()-1);
                startActivity(addBtn);
            }

            private FragmentManager getSupportFragmentManager() {
                return null;
            }
        });


        /**
         Set layoutManager .It's setter. linear is vertical
         getActivity stay in fragment.
         **/

        updateUI();
        return v; //return view to use up to you.
    }


    /**
     * Update UI
     */
    private void updateUI(){
        KeepWalkLab keepLab = KeepWalkLab.getInstance(getActivity());//Call Instance and get object
        List<KeepWalk> keepp = keepLab.getKeepList();
        if(_adapter==null){
            _adapter = new KeepAdapter(keepp); //Create object by call CrimeAdapter
            _keepRecyclerView.setAdapter(_adapter); //set adapter but I don't know when crimeRecyclerView work.
        }else{
//            _adapter.notifyDataSetChanged();
            if(keepPos != null){
                for(Integer pos : keepPos){
                    _adapter.notifyItemChanged(pos);
                    Log.d(TAG, "notify change at " + pos);
                }

            }


        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"Resume list");
        updateUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_UPDATED_KEEPWALK){
            if(resultCode == Activity.RESULT_OK){
                keepPos = (Integer[]) data.getExtras().get("position");
                Log.d(TAG, "get crimePos=" + keepPos);
            }
            //Blah blah
            Log.d(TAG,"Return form CrimeFragment ");
        }
    }


    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView _titleTextView;
        public TextView _dateTextView;
        int _position;
        KeepWalk _keepp;


        public CrimeHolder(View itemView) {
            super(itemView);
            _titleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_keepwalk_title_text_view);
            _dateTextView =(TextView)
                    itemView.findViewById(R.id.list_item_crime_date_text_view);
            itemView.setOnClickListener(this); //plus OnClickListener

        }

        public void bind(KeepWalk crime,int position) {
            _keepp = crime;
            _position = position;
            _titleTextView.setText(_keepp.getTitle());
            _dateTextView.setText(_keepp.getKeepDate().toString());
        }

        /**
         * CrimeHolder
         * onclick Method
         */
        @Override
        public void onClick(View view) {
//            Toast.makeText(getActivity(),
//                    "Press!"+_titleTextView.getText(),
//                    Toast.LENGTH_SHORT)
//                    .show();
            Log.d(TAG, "Send position ; "+ _position);
            Intent intent = KeepWalkPagerActivity.newIntent(getActivity(), _keepp.getId(),_position);//Call Method newIntent and sent
            startActivityForResult(intent, REQUEST_UPDATED_KEEPWALK);//Call Method of Fragment class
        }
    }
    private class KeepAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<KeepWalk> _keepp;
        private int viewCreatingCount;
        public  KeepAdapter(List<KeepWalk> keep){this._keepp = keep;} //ArrayList

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            viewCreatingCount++;
            Log.d(TAG,"Create view holder for CrimeList : creating view time = "+ viewCreatingCount);
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity()); // from is statics method LayoutInflater stay in getActivity
            View v = layoutInflater.inflate(R.layout.list_item_keepwalk,parent,false); // Draw , Create TextView 2txt
            return new CrimeHolder(v);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Log.d(TAG,"Bind view holder for CrimeList : position = " + position);
            KeepWalk keep = _keepp.get(position);
            holder.bind(keep,position); // get crime object to use
        }

        @Override
        public int getItemCount() {
            return _keepp.size();
        }
    }

}
