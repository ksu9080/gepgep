package com.example.gepgep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FairingFragment fairingFragment;
    TranslateFragment translateFragment;
    SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fairingFragment = new FairingFragment();
        translateFragment = new TranslateFragment();
        settingFragment = new SettingFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fairingFragment).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab1:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fairingFragment).commit();
                    return true;

                case R.id.tab2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, translateFragment).commit();
                    return true;

                case R.id.tab3:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
                    return true;
            }
            return false;
        });
    }
}
