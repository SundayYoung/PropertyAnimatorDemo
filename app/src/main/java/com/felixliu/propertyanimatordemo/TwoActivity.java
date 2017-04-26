package com.felixliu.propertyanimatordemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.felixliu.propertyanimatordemo.view.MyView;


public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        final MyView myView = (MyView) findViewById(R.id.my_view);

        Button button = (Button) findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myView.startAnimator();
            }
        });

    }
}
