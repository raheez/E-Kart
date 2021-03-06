package com.example.muhammedraheezrahman.e_commerceapp;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class SplashScreen extends RootActivity {
    int splashScreenDuration = 4000;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        imageView = (ImageView) findViewById(R.id.logo_image);

        propagateToMainActivity();
        pumpHeart();
    }

    private void propagateToMainActivity() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },splashScreenDuration);

    }
    private void pumpHeart() {
        imageView.animate().scaleXBy(0.2f).scaleYBy(0.2f).setDuration(100).setListener(scaleUpListener);

    }

    private Animator.AnimatorListener scaleDownListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            imageView.animate().scaleXBy(0.2f).scaleYBy(0.2f).setDuration(250).setListener(scaleUpListener);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            // TODO Auto-generated method stub

        }
    };

    private Animator.AnimatorListener scaleUpListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            imageView.animate().scaleXBy(-0.2f).scaleYBy(-0.2f).setDuration(250).setListener(scaleDownListener);

        }

        @Override
        public void onAnimationCancel(Animator animation) {
            // TODO Auto-generated method stub

        }
    };
}
