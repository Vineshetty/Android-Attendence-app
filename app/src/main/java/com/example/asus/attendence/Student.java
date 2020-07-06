package com.example.asus.attendence;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Student extends AppCompatActivity {

    SQLiteDatabase db;
    TextView t1,t2,t3;
    TextView br[]=new TextView[80];
    TableRow tb[]=new TableRow[80];
    TableLayout tk;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        int j=0;
        db=this.openOrCreateDatabase("Student",MODE_PRIVATE,null);
        t1=(TextView)findViewById(R.id.textView10);
        t2=(TextView)findViewById(R.id.textView11);
        t3=(TextView)findViewById(R.id.textView12);
        Intent i=getIntent();
        String n=i.getStringExtra("name");
        String u=i.getStringExtra("pass");
        t1.setText(n);
        t2.setText(u);
        tk=(TableLayout)findViewById(R.id.tb4);

        br[j]=new TextView(getApplicationContext());
        br[j+1]=new TextView(getApplicationContext());
        tb[j] = new TableRow(getApplicationContext());

        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        br[j].setId(j);
       br[j+1].setId(j+100);
        tb[j].setId(j);
        br[j].setTextSize(30);
      br[j+1].setTextSize(30);
        br[j].setTextColor(Color.WHITE);
        br[j+1].setTextColor(Color.WHITE);
        tb[j].setBackgroundColor(Color.MAGENTA);
        br[j].setText("SUBJECT   ");
       br[j+1].setText("ATTANDENCE");

        tb[j].addView(br[j]);
      tb[j].addView(br[j+1]);
        tk.addView(tb[j],0);
        Cursor c=db.rawQuery("select * from CSE where name='"+n+"' and usn='"+u+"';",null);
        c.moveToFirst();


       j=2;
      br[j]=new TextView(getApplicationContext());
        br[j+1]=new TextView(getApplicationContext());
        tb[1] = new TableRow(getApplicationContext());

         br[j].setId(j+100);
            br[j].setText("          "+c.getColumnName(4));
            br[j+1].setId(j+1+100);
            br[j+1].setText("             "+c.getInt(4)+" ");
            br[j].setTextSize(30);
            br[j].setTextColor(Color.RED);
            br[j+1].setTextSize(30);
            br[j+1].setTextColor(Color.BLUE);
            tb[1].addView(br[j]);
            tb[1].addView(br[j+1]);
            tb[1].setBackgroundColor(Color.WHITE);
        tk.addView(tb[1],1);
        j=4;
        br[j]=new TextView(getApplicationContext());
        br[j+1]=new TextView(getApplicationContext());
        tb[2] = new TableRow(getApplicationContext());

        br[j].setId(j+100);
        br[j].setText("          "+c.getColumnName(5));
        br[j+1].setId(j+1+100);
        br[j+1].setText("             "+c.getInt(5)+" ");
        br[j].setTextSize(30);
        br[j].setTextColor(Color.RED);
        br[j+1].setTextSize(30);
        br[j+1].setTextColor(Color.BLUE);
        tb[2].addView(br[j]);
        tb[2].addView(br[j+1]);
        tb[2].setBackgroundColor(Color.LTGRAY);
        tk.addView(tb[2],2);











    }
}
