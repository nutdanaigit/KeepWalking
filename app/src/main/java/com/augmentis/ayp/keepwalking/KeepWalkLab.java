package com.augmentis.ayp.keepwalking;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Nutdanai on 7/27/2016.
 */
public class KeepWalkLab {
    List<KeepWalk> keepList;

    private static KeepWalkLab instance;

    public static KeepWalkLab getInstance(Context context) {
//        KeepWalkLab v1 = new KeepWalkLab();
//        KeepWalkLab v2 = new KeepWalkLab();
//        v1.keepList = v2.keepList; // = is Refer because v1 v2 are object
        if (instance == null) {
            instance = new KeepWalkLab();
        }
        return instance;
    }

    public KeepWalkLab() {
        if(keepList==null){
            keepList = new ArrayList<>();
        }
//        KeepWalk keep = new KeepWalk();
//        keepList.add(keep);
    }

    public KeepWalk getCrimeById(UUID uuid) {
        for (KeepWalk keep : keepList) {
            if (keep.getId().equals(uuid))
                return keep;
        }
        return null;
    }

    public int getCrimePositionById(UUID uuid) {
        int size = keepList.size();
        for (int i = 0; i < size; i++) {
            if (keepList.get(i).getId().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
    public List<KeepWalk> getKeepList(){
        return this.keepList;
    }

    public static void main(String [] args){
        KeepWalkLab keepLab = KeepWalkLab.getInstance(null);
        List<KeepWalk> keepList = keepLab.getKeepList();
        int size = keepList.size();
        for(int i=0;i<size;i++){
            System.out.println(keepList.get(i));
        }
//        System.out.println(crimeLab.toString());
//        System.out.println(crimeLab.getInstance());
    }
    public void addKeep(String title){
        KeepWalk temp = new KeepWalk();
        temp.setTitle(title);
        keepList.add(temp);
    }



//    public void cleanNull(){
//        for (KeepWalk clean:keepList) {
//            if (clean.getTitle().equals("")){
//                keepList.remove(clean);
//            }
//        }
//    }


}
