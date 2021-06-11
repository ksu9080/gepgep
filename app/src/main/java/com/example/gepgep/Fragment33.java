package com.example.gepgep;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment33 extends Fragment {
    private TextView preView,mail;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_33, container, false);
        TextView mail = (TextView) v.findViewById(R.id.mail);

        String content = mail.getText().toString();
        SpannableString spannableString = new SpannableString(content);

        String word = "0000@koreatech.ac.kr";
        int start = content.indexOf(word);
        int end = start + word.length();

        spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(1.2f), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        mail.setText(spannableString);

        preView = (TextView) v.findViewById(R.id.preView);
        preView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout3, new SettingFragment()).commit();
            }
        });
        return v;
    }
}