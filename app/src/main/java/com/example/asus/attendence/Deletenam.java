package com.example.asus.attendence;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Deletenam extends AppCompatActivity implements View.OnClickListener {
    EditText e1;
    Button b1;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletenam);
        e1=(EditText)findViewById(R.id.editText10);
        b1=(Button)findViewById(R.id.button8);
        db=this.openOrCreateDatabase("Student",MODE_PRIVATE,null);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String n=e1.getText().toString();
        db.execSQL("delete from CSE where name='"+n+"';");
        Toast.makeText(this, "Deleted Sucessfully", Toast.LENGTH_SHORT).show();
    }
}
