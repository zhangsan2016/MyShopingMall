package com.ldgd.com.myshopingmall.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import bletext.ldgd.com.myshopingmall.R;

public class Welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = new Intent(Welcome.this, MainActivity.class);
        Welcome.this.startActivity(intent);
        finish();

  /*      new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome.this, MainActivity.class);
                Welcome.this.startActivity(intent);
                finish();

            }
        },2000);*/
    }
}
