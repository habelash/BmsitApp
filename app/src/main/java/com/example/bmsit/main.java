package com.example.bmsit;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class main extends Fragment {

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstenceState){

        View rootview = inflater.inflate(R.layout.main,container,false);
        return rootview;
    }
}
