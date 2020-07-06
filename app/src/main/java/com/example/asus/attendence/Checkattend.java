package com.example.asus.attendence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Checkattend extends AppCompatActivity {
    TextView t[]=new TextView[80];
    TextView t1[]=new TextView[80];
    TextView t2[]=new TextView[80];
    Spinner sp;
    String semsect;
    ArrayAdapter ad;
    String br[]={"CSE6A","CSE6B","CSE6C","CSE6D"};

    int count=0;

    TableRow tb[]=new TableRow[80];

    TableLayout tl;
    Button b;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkattend);

        b=(Button)findViewById(R.id.button10);
        sp=(Spinner)findViewById(R.id.spinner2);
        ad=new ArrayAdapter<String>(this,R.layout.l1,br);
        sp.setAdapter(ad);
        sp.post(new Runnable() {
            @Override
            public void run() {
                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String s=ad.getItem(position).toString();
                        /*Toast.makeText(Attendlist.this, s, Toast.LENGTH_SHORT).show();*/
                        loadpage(s);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

        /*db=this.openOrCreateDatabase("Students",MODE_PRIVATE,null);
*/
        db = this.openOrCreateDatabase("Student", MODE_PRIVATE, null);
       /* db.execSQL("create table if not exists testing(name varchar(20),usn varchar(20),branch varchar(20),semsec varchar(10),attn int);");
*/
       /* db.execSQL("create table if not exists CSE(name varchar(20),usn varchar(20),branch varchar(20),semsec varchar(10),attn int);");
*/
        tl=(TableLayout)findViewById(R.id.tb2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadpage("CSE");
            }
        });





    }
    public void loadpage(String s) {

        Toast.makeText(Checkattend.this, "okkkk", Toast.LENGTH_SHORT).show();

        String sk[]=s.split("E");
        semsect=sk[1];


        Cursor cursor = db.rawQuery("select * from CSE where semsec='"+sk[1]+"';", null);
        if(cursor==null)
        {
            Toast.makeText(this, "No entries", Toast.LENGTH_SHORT).show();
        }
        else {
            count=cursor.getCount();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String str = "";
                str = cursor.getString(0);
                int no1=cursor.getInt(4);
                int no2=cursor.getInt(5);
                t[i] = new TextView(getApplicationContext());
                t1[i] = new TextView(getApplicationContext());
                t2[i] = new TextView(getApplicationContext());



                tb[i] = new TableRow(getApplicationContext());

                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

                t[i].setId(i+100);
                t1[i].setId(i+200);
                t2[i].setId(i+300);

                tb[i].setId(i);
                t[i].setText(str+"            ");
                t1[i].setText(" "+no1+"          ");
                t2[i].setText(no2+" ");

                t[i].setTextColor(Color.BLUE);
                t[i].setTextSize(30);
                t1[i].setTextColor(Color.BLUE);
                t1[i].setTextSize(30);
                t2[i].setTextColor(Color.BLUE);
                t2[i].setTextSize(30);

                tb[i].addView(t[i]);
                tb[i].addView(t1[i]);
                tb[i].addView(t2[i]);

                if ((i % 2 == 0)) {
                    tb[i].setBackgroundColor(Color.LTGRAY);

                } else {
                    tb[i].setBackgroundColor(Color.WHITE);
                }



                tl.addView(tb[i], i);

            }
        }



    }


}
