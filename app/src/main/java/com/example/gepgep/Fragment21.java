package com.example.gepgep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gepgep.Fragment22;
import com.example.gepgep.R;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class Fragment21 extends Fragment {

    private TextView text;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView del, del1, del2, del3, del4;
    private TextView searchtext;

    // 공유 변수
    static public String Send_21_to_22 = "";
    static public int before = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_21, container, false);

        // 객체 선언
        text = (TextView) v.findViewById(R.id.text); // 이전에 입력한 값 일단은 그냥 텍스트 뷰
        text1 = (TextView) v.findViewById(R.id.text1); // 이전에 입력한 값 일단은 그냥 텍스트 뷰
        text2 = (TextView) v.findViewById(R.id.text2); // 이전에 입력한 값 일단은 그냥 텍스트 뷰
        text3 = (TextView) v.findViewById(R.id.text3); // 이전에 입력한 값 일단은 그냥 텍스트 뷰
        text4 = (TextView) v.findViewById(R.id.text4); // 이전에 입력한 값 일단은 그냥 텍스트 뷰

        del = (TextView) v.findViewById(R.id.del); // 이전에 입력한 값 일단은 그냥 텍스트 뷰
        del1 = (TextView) v.findViewById(R.id.del1); // 이전에 입력한 값 일단은 그냥 텍스트 뷰
        del2 = (TextView) v.findViewById(R.id.del2); // 이전에 입력한 값 일단은 그냥 텍스트 뷰
        del3 = (TextView) v.findViewById(R.id.del3); // 이전에 입력한 값 일단은 그냥 텍스트 뷰
        del4 = (TextView) v.findViewById(R.id.del4); // 이전에 입력한 값 일단은 그냥 텍스트 뷰

        text.setText(Fragment22.Send_22_to_21[0]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
        text1.setText(Fragment22.Send_22_to_21[1]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
        text2.setText(Fragment22.Send_22_to_21[2]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
        text3.setText(Fragment22.Send_22_to_21[3]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
        text4.setText(Fragment22.Send_22_to_21[4]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.

        searchtext = (TextView) v. findViewById(R.id.searchText); // 입력창에 대한 객체 선언

        // 입력텍스트쪽 누르면 frament22로 전환
        text = (TextView) v.findViewById(R.id.text); // 이전에 입력한 값 일단은 그냥 텍스트 뷰


        searchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (before == 1)
                {
                    Send_21_to_22 = searchtext.getText().toString();
                }
                getFragmentManager().beginTransaction().replace(R.id.frameLayout2, new Fragment22()).commit();
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchtext.setText(Fragment22.Send_22_to_21[0]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                before = 1;
                Send_21_to_22 = searchtext.getText().toString();
                getFragmentManager().beginTransaction().replace(R.id.frameLayout2, new Fragment22()).commit();
            }
        });

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchtext.setText(Fragment22.Send_22_to_21[1]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                before = 1;
                Send_21_to_22 = searchtext.getText().toString();
                getFragmentManager().beginTransaction().replace(R.id.frameLayout2, new Fragment22()).commit();
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchtext.setText(Fragment22.Send_22_to_21[2]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                before = 1;
                Send_21_to_22 = searchtext.getText().toString();
                getFragmentManager().beginTransaction().replace(R.id.frameLayout2, new Fragment22()).commit();
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchtext.setText(Fragment22.Send_22_to_21[3]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                before = 1;
                Send_21_to_22 = searchtext.getText().toString();
                getFragmentManager().beginTransaction().replace(R.id.frameLayout2, new Fragment22()).commit();
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchtext.setText(Fragment22.Send_22_to_21[4]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                before = 1;
                Send_21_to_22 = searchtext.getText().toString();
                getFragmentManager().beginTransaction().replace(R.id.frameLayout2, new Fragment22()).commit();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment22.Send_22_to_21[0] = "";

                for (int i = 0; i<4; i++) // 당겨오기
                {
                    Fragment22.Send_22_to_21[i] = Fragment22.Send_22_to_21[i+1];
                }

                Fragment22.Send_22_to_21[4] = "";

                text.setText(Fragment22.Send_22_to_21[0]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                text1.setText(Fragment22.Send_22_to_21[1]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                text2.setText(Fragment22.Send_22_to_21[2]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                text3.setText(Fragment22.Send_22_to_21[3]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                text4.setText(Fragment22.Send_22_to_21[4]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
            }
        });

        del1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment22.Send_22_to_21[1] = "";

                for (int i = 1; i<4; i++) // 당겨오기
                {
                    Fragment22.Send_22_to_21[i] = Fragment22.Send_22_to_21[i+1];
                }

                Fragment22.Send_22_to_21[4] = "";

                text1.setText(Fragment22.Send_22_to_21[1]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                text2.setText(Fragment22.Send_22_to_21[2]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                text3.setText(Fragment22.Send_22_to_21[3]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                text4.setText(Fragment22.Send_22_to_21[4]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
            }
        });

        del2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment22.Send_22_to_21[2] = "";

                for (int i = 2; i<4; i++) // 당겨오기
                {
                    Fragment22.Send_22_to_21[i] = Fragment22.Send_22_to_21[i+1];
                }

                Fragment22.Send_22_to_21[4] = "";

                text2.setText(Fragment22.Send_22_to_21[2]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                text3.setText(Fragment22.Send_22_to_21[3]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                text4.setText(Fragment22.Send_22_to_21[4]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
            }
        });

        del3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment22.Send_22_to_21[3] = "";

                for (int i = 3; i<4; i++) // 당겨오기
                {
                    Fragment22.Send_22_to_21[i] = Fragment22.Send_22_to_21[i+1];
                }

                Fragment22.Send_22_to_21[4] = "";

                text3.setText(Fragment22.Send_22_to_21[3]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
                text4.setText(Fragment22.Send_22_to_21[4]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
            }
        });

        del4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment22.Send_22_to_21[4] = "";
                text4.setText(Fragment22.Send_22_to_21[4]); // 22에 있는 데이터 0번 값을 불러와서 이전값으로 갱신한다.
            }
        });

        return v;
    }
}