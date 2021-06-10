package com.shopThuCung.duanshopthucung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopThuCung.duanshopthucung.Login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    private Animation topAnim, bottomAnim;
    private ImageView image;
    private TextView tvLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        // Hooks
        image = findViewById(R.id.imageView);
        tvLogo = findViewById(R.id.tvLogo);

        image.setAnimation(topAnim);
        tvLogo.setAnimation(bottomAnim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_TIME_OUT);
    }
}