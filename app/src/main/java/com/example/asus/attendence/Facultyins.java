package com.example.asus.attendence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Facultyins extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    TextView t1,t2,t3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyins);
        db=this.openOrCreateDatabase("Student",MODE_PRIVATE,null);
        db.execSQL("create table if not exists faculty(name varchar(20),password varchar(20),dept varchar(20));");
        t1=(TextView)findViewById(R.id.editText5);
                t2=(TextView)findViewById(R.id.editText8);
        t3=(TextView)findViewById(R.id.editText7);
        b1=(Button)findViewById(R.id.button9);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String n=t1.getText().toString();
        String p=t2.getText().toString();
        String d=t3.getText().toString();
        db.execSQL("insert into faculty values('"+n+"','"+p+"','"+d+"');");
        Toast.makeText(this, "Insertion Sucessfull", Toast.LENGTH_SHORT).show();
    }
}
