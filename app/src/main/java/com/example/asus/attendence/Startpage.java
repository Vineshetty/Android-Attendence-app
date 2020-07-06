package com.example.asus.attendence;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Startpage extends AppCompatActivity {
    RelativeLayout rl;
    TextView t1,t2;
    private  static int spt=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
        rl=(RelativeLayout)findViewById(R.id.activity_startpage);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);

        t1.setTextColor(Color.WHITE);
        t2.setTextColor(Color.WHITE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Startpage.this,Home.class);
                startActivity(i);
                finish();

            }
        },spt);
    }

}
