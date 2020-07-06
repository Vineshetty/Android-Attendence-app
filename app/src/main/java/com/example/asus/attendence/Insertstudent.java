package com.example.asus.attendence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Insertstudent extends AppCompatActivity implements View.OnClickListener {
    EditText name, semsec, usn,branch;
    Button b1;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertstudent);
     /* db = new S3(this).getWritableDatabase();*/


        name = (EditText) findViewById(R.id.editText3);
        semsec = (EditText) findViewById(R.id.editText4);
        branch = (EditText) findViewById(R.id.editText9);
        usn = (EditText) findViewById(R.id.editText6);
        b1 = (Button) findViewById(R.id.button6);


        b1.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        db = this.openOrCreateDatabase("Student", MODE_PRIVATE, null);

        db.execSQL("create table if not exists CSE(name varchar(20),usn varchar(20),branch varchar(20),semsec varchar(10),CD int,CN int);");

        String n = name.getText().toString();
        String u = usn.getText().toString();
        String ss = semsec.getText().toString();
        String br = branch.getText().toString();

              /*  String tab=br+ss;*/


        name.getText().clear();
        usn.getText().clear();
        semsec.getText().clear();
        branch.getText().clear();

        db.execSQL("insert into CSE values('" + n + "','" + u + "','" + br + "','" + ss + "'," + 0 + ","+0+");");




        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

        db.close();
    }









}
