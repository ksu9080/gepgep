package com.example.gepgep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment31 extends Fragment {

    private TextView set11,set12,set21,set22,set31,set32,preView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_31, container, false);

        //프래그먼트 전환
       preView = (TextView) v.findViewById(R.id.preView);
       preView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new SettingFragment()).commit();
            }
        });
        set11 = (TextView) v.findViewById(R.id.set11);
        set11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new Fragment311()).commit();
            }
        });
        set12 = (TextView) v.findViewById(R.id.set12);
        set12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new Fragment311()).commit();
            }
        });
        set21 = (TextView) v.findViewById(R.id.set21);
        set21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new Fragment311()).commit();
            }
        });
        set22 = (TextView) v.findViewById(R.id.set22);
        set22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new Fragment311()).commit();
            }
        });
        set31 = (TextView) v.findViewById(R.id.set31);
        set31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new Fragment311()).commit();
            }
        });
        set32 = (TextView) v.findViewById(R.id.set11);
        set32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new Fragment311()).commit();
            }
        });

        return v;
    }
}