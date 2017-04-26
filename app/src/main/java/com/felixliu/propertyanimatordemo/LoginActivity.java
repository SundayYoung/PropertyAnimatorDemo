package com.felixliu.propertyanimatordemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.felixliu.propertyanimatordemo.view.MyInterpolator;

public class LoginActivity extends AppCompatActivity {

    LinearLayout usernameLayout;
    LinearLayout passwordLayout;
    View accountLayout;
    View progressLayout;

    private int viewWidth,viewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLayout = (LinearLayout) findViewById(R.id.username_layout);
        passwordLayout = (LinearLayout) findViewById(R.id.password_layout);
        accountLayout = findViewById(R.id.account_layout);
        progressLayout = findViewById(R.id.progress_layout);

        TextView textView = (TextView) findViewById(R.id.main_btn_login);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameLayout.setVisibility(View.INVISIBLE);
                passwordLayout.setVisibility(View.INVISIBLE);

                initAnim();
            }
        });
    }

    private void initAnim() {
        AnimatorSet set = new AnimatorSet();

        ValueAnimator animator = ValueAnimator.ofFloat(0, viewWidth);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) accountLayout
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                accountLayout.setLayoutParams(params);
            }
        });

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(accountLayout,
                "scaleX", 1f, 0.5f);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new LinearInterpolator());
//        animator.playTogether(objectAnimator);
        objectAnimator.start();

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                progressLayout.setVisibility(View.VISIBLE);
                accountLayout.setVisibility(View.INVISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressAnimator();
                        Intent intent = new Intent(LoginActivity.this, TwoActivity.class);
                        startActivity(intent);
                    }
                },3000);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void progressAnimator() {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(progressLayout,
                animator, animator2);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new MyInterpolator());
        objectAnimator.start();

    }
}
