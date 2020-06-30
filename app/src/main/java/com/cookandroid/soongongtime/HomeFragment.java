package com.cookandroid.soongongtime;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    View view;
    Chronometer timer1;
    Chronometer timer2;
    ToggleButton tbTimer1;
    ToggleButton tbTimer2;
    Button btnRecord;
    TextView tvPercent;
    //timer1이 처음 시작부터 현재 몇초 흘렀는지 저장해둠
    long flowTime = 0;

    long time1;
    long time2;
    Drawable blue_drawable, red_drawable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        timer1 = (Chronometer)view.findViewById(R.id.timer1);
        timer2 = (Chronometer)view.findViewById(R.id.timer2);
        tbTimer1 = (ToggleButton)view.findViewById(R.id.tbTimer1);
        tbTimer2 = (ToggleButton)view.findViewById(R.id.tbTimer2);
        btnRecord = (Button)view.findViewById(R.id.btnRecord);
        tvPercent = (TextView)view.findViewById(R.id.tvPercent);

        blue_drawable= ContextCompat.getDrawable(view.getContext(),R.drawable.shape_blue);
        red_drawable= ContextCompat.getDrawable(view.getContext(),R.drawable.shape_red);

        //책상버튼이 꺼져있을 때의 초기 화면, 순공버튼이 비활성으로 시작해야 함
        tbTimer1.setEnabled(false);
        tbTimer1.setChecked(true);
        tbTimer1.setBackgroundDrawable(red_drawable);


        //순공시간 타이머
        tbTimer1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //공부 중
                if(tbTimer1.isChecked()){
                    tbTimer1.setBackgroundDrawable(red_drawable);
                    //현재 앱이 흐른 시간을 기준으로 초를 세기때문에, 실제로 추가되야하는 초만큼 빼서 기준점을 앞으로 돌려 준 것
                    timer1.setBase(SystemClock.elapsedRealtime() - flowTime);
                    timer1.start();
                }

                //휴식 중
                else{
                    tbTimer1.setBackgroundDrawable(blue_drawable);
                    flowTime = SystemClock.elapsedRealtime() - timer1.getBase();
                    timer1.stop();
                }

            }
        });

        //책상시간 타이머
        tbTimer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tbTimer2.isChecked()){
                    timer1.setBase(SystemClock.elapsedRealtime());
                    timer1.start();
                    timer2.setBase((SystemClock.elapsedRealtime()));
                    timer2.start();
                    tbTimer1.setEnabled(true);
                }

                else{
                    timer1.stop();
                    timer2.stop();
                    tbTimer1.setEnabled(false);
                    flowTime=0;
                }

            }
        });

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        timer1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                time1 = (SystemClock.elapsedRealtime()-timer1.getBase())/1000;
            }
        });

        timer2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                time2 = (SystemClock.elapsedRealtime()-timer2.getBase())/1000;
                int percent = (int)((float)time1/time2*100.0);
                tvPercent.setText(String.valueOf(percent+"%"));

            }
        });


        return view;
    }

}
