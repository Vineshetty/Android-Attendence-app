package com.example.asus.attendence;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Attendlist extends AppCompatActivity{
    ArrayAdapter adr;
    LinearLayout llli;
    String semsect;
    TableLayout tl;
    Spinner sp;
    ArrayAdapter ad;
    String clas[]={"CSE6A","CSE6B","CSE6C","CSE6D"};
    int count;

    RadioGroup radgrop[]=new RadioGroup[80];
    RadioButton rp[]=new RadioButton[80];
    RadioButton ra[]=new RadioButton[80];
    TextView t[]=new TextView[80];
    TextView br[]=new TextView[80];
    TableRow tb[]=new TableRow[80];

    SpeechRecognizer mspechrec;
    Intent intent;
    TextView voiceinput;
    ImageView micbutton;



    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendlist);
       /* db = new S3(this).getWritableDatabase();*/
        db=this.openOrCreateDatabase("Student",MODE_PRIVATE,null);

      /*  db = this.openOrCreateDatabase("Students", MODE_PRIVATE, null);
      */ /* db.execSQL("create table if not exists testing(name varchar(20),usn varchar(20),branch varchar(20),semsec varchar(10),attn int);");

        db.execSQL("create table if not exists CS6A(name varchar(20),usn varchar(20),branch varchar(20),semsec varchar(10),attn int);");

*/

      /*  llli = (LinearLayout) findViewById(R.id.ll1);*/
        tl=(TableLayout)findViewById(R.id.tb1);



        adr=new ArrayAdapter<String>(this,R.layout.l1,clas);
        sp=(Spinner)findViewById(R.id.spinner);

        sp.setAdapter(adr);
        sp.post(new Runnable() {
            @Override
            public void run() {
                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String s=adr.getItem(position).toString();
                        /*Toast.makeText(Attendlist.this, s, Toast.LENGTH_SHORT).show();*/
                        loadpage(s);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });





        mspechrec=SpeechRecognizer.createSpeechRecognizer(this);

        intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());



        mspechrec.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    String s1 = matches.get(0);
                    marks(s1);

                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });




        findViewById(R.id.imageView2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mspechrec.stopListening();
                       /*can enetr the textview here*/
                        break;
                    case MotionEvent.ACTION_DOWN:
                       /*blank showing*/
                        /*here put listening*/
                        mspechrec.startListening(intent);
                        break;
                }

                return false;
            }
        });



    }



    public void loadpage(String s) {

        Toast.makeText(Attendlist.this, "okkkk", Toast.LENGTH_SHORT).show();
        String sk[]=s.split("E");
        semsect=sk[1];


        Cursor cursor = db.rawQuery("select * from CSE where semsec='"+sk[1]+"';", null);
        if(cursor==null)
        {
            Toast.makeText(this, "No entries", Toast.LENGTH_SHORT).show();
        }
        else {


          /*  ArrayList ar = new ArrayList<String>();*/
            count=cursor.getCount();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String str = "";
                str = cursor.getString(0);
            /*+ " " + cursor.getString(1)*/

                t[i] = new TextView(getApplicationContext());
                br[i]=new TextView(getApplicationContext());
                radgrop[i] = new RadioGroup(getApplicationContext());
                rp[i] = new RadioButton(getApplicationContext());

                ra[i] = new RadioButton(getApplicationContext());
                tb[i] = new TableRow(getApplicationContext());

                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

                t[i].setId(i);
                br[i].setId(i+80);
            /*radgrop[i].setId(i);*/
                radgrop[i].setOrientation(LinearLayout.HORIZONTAL);

                rp[i].setId(i);
                ra[i].setId(i + 80);


                tb[i].setId(i);

                t[i].setText(str);
                br[i].setText("       ");
                t[i].setTextColor(Color.BLUE);
                t[i].setTextSize(30);


                rp[i].setChecked(true);
                rp[i].setText("                               ");

                radgrop[i].addView(rp[i]);
                radgrop[i].addView(ra[i]);


                tb[i].addView(t[i]);
                tb[i].addView(br[i]);
                tb[i].addView(radgrop[i]);

                if ((i % 2 == 0)) {
                    tb[i].setBackgroundColor(Color.GRAY);

                } else {
                    tb[i].setBackgroundColor(Color.WHITE);
                }



                tl.addView(tb[i], i);



           /* check[i] = new CheckBox(getApplicationContext());

            check[i].setId(i);
            check[i].setText(str);
            check[i].setOnCheckedChangeListener(this);
            check[i].setTextColor(Color.BLUE);
            check[i].setTextSize(20);
            check[i].setChecked(true);*/

            /*llli.addView(check[i]);*/
            }
        }



    }

    public void marks(String name)

    {String sub[]=name.split(" ");


        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        if (sub[0].equals("update")) {

            Cursor c = db.rawQuery("select * from CSE where semsec='"+ semsect+"';", null);
            c.moveToFirst();
            for (int i = 0; i < count; i++) {
                String nme =t[i].getText().toString();

                if(ra[i].isChecked())
                {
                    c.moveToNext();
                }
                else {
                    int x = c.getInt(4) + 1;
                    db.execSQL("update CSE set " + sub[1] + "=" + x + " where name='" + nme + "';");

                    Toast.makeText(this, sub[1]+" was updated sucessfully", Toast.LENGTH_SHORT).show();
                    c.moveToNext();
                }
            }


        } else {
            String res[] = name.split(" ");
            if (res[1].equals("absent") || res[1].equals("accent")) {


                for (int i = 0; i < count; i++) {

                    if (res[0].equals(t[i].getText().toString())) {
                       tb[i].setBackgroundColor(Color.RED);
                        ra[i].setChecked(true);

                    }

                }
            }

            if (res[1].equals("present")) {


                for (int i = 0; i < count; i++) {

                    if (res[0].equals(t[i].getText().toString())) {
                        tb[i].setBackgroundColor(Color.GREEN);
                        rp[i].setChecked(true);

                    }

                }
            }

        }
    }



}


