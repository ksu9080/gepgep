package com.example.gepgep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SettingFragment extends Fragment {

    private TextView setText11,setText12,setText31,setText32;
    private TextView skin0,skin1,skin2,skin3,skin4,skin5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_setting, container, false);

        //테마변경
        skin0 = (TextView) v.findViewById(R.id.skin0);
        skin1 = (TextView) v.findViewById(R.id.skin1);
        skin2 = (TextView) v.findViewById(R.id.skin2);
        skin3 = (TextView) v.findViewById(R.id.skin3);
        skin4 = (TextView) v.findViewById(R.id.skin4);
        skin5 = (TextView) v.findViewById(R.id.skin5);
        skin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skin0.setBackgroundResource(R.drawable.skin1_back_12);
                skin0.setText("노란색");
            }
        });
        skin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skin0.setBackgroundResource(R.drawable.skin2_back_12);
                skin0.setText("민트색");
            }
        });
        skin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skin0.setBackgroundResource(R.drawable.skin3_black_12);
                skin0.setText("초록색");
            }
        });
        skin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skin0.setBackgroundResource(R.drawable.skin4_back_12);
                skin0.setText("붉은색");
            }
        });
        skin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skin0.setBackgroundResource(R.drawable.skin5_back_12);
                skin0.setText("분홍색");
            }
        });

        //버튼 클릭시 화면전환
        setText11 = (TextView) v.findViewById(R.id.setText11);
        setText11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new Fragment31()).commit();
            }
        });
        setText12 = (TextView) v.findViewById(R.id.setText12);
        setText12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new Fragment31()).commit();
            }
        });
        setText31 = (TextView) v.findViewById(R.id.setText31);
        setText31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new Fragment33()).commit();
            }
        });
        setText32 = (TextView) v.findViewById(R.id.setText32);
        setText32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new Fragment33()).commit();
            }
        });



        return v;
    }

}