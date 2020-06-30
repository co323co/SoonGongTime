package com.cookandroid.soongongtime;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity{

    FragmentManager manager;
    HomeFragment homeFragment;
    LeftFragment leftFragment;
    RightFragment rightFragment;
    ImageButton btnLeft;
    ImageButton btnRight;
    ImageButton btnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        leftFragment = new LeftFragment();
        rightFragment = new RightFragment();

        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnLeft = (ImageButton) findViewById(R.id.btnLeft);
        btnRight = (ImageButton) findViewById(R.id.btnRight);


        FragmentTransaction ft = manager.beginTransaction();
        //ft.addToBackStack(null);
        ft.add(R.id.main_container, homeFragment);
        ft.add(R.id.main_container, leftFragment);
        ft.add(R.id.main_container, rightFragment);
        ft.hide(leftFragment);
        ft.hide(rightFragment);
        ft.commit();
        buttonEffect(btnHome,true);
        buttonEffect(btnLeft,false);
        buttonEffect(btnRight,false);

        btnHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!homeFragment.isVisible()) {
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.show(homeFragment);
                    ft.hide(leftFragment);
                    ft.hide(rightFragment);
                    ft.commit();
                    buttonEffect(btnHome,true);
                    buttonEffect(btnLeft,false);
                    buttonEffect(btnRight,false);
                }
            }
        });

        btnLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!leftFragment.isVisible()) {
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.show(leftFragment);
                    ft.hide(homeFragment);
                    ft.hide(rightFragment);
                    ft.commit();
                    buttonEffect(btnHome,false);
                    buttonEffect(btnLeft,true);
                    buttonEffect(btnRight,false);
                }
            }
        });

        btnRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rightFragment.isVisible()) {
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.show(rightFragment);
                    ft.hide(homeFragment);
                    ft.hide(leftFragment);
                    ft.commit();
                    buttonEffect(btnHome,false);
                    buttonEffect(btnLeft,false);
                    buttonEffect(btnRight,true);
                }
            }
        });
    }

    //이미지 버튼의 아이콘 상태를 바꿔줌
    void buttonEffect(ImageButton btn, boolean state)
    {

        int padding;
        int color;

        if (state==true) {
            padding=30;
            color=R.color.white;
        }
        else {
            padding=20;
            color=R.color.softGray;
        }
        btn.setPadding(padding,padding,padding,padding);
        //btn.setBackgroundColor(ContextCompat.getColor(MainActivity.this,color));
        btn.setColorFilter(ContextCompat.getColor(MainActivity.this,color), PorterDuff.Mode.MULTIPLY);
    }

}




