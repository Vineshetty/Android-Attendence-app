package com.example.asus.attendence;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Locale;

public class Home extends AppCompatActivity implements View.OnClickListener {
    EditText name, pass;
    SpeechRecognizer mspechrec;
    Intent intent;
    ImageView micbutton;
    TextView voiceinput;
    Button b1, b2, b3;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mspechrec = SpeechRecognizer.createSpeechRecognizer(this);

        name = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button11);
        b3 = (Button) findViewById(R.id.button3);
        db=this.openOrCreateDatabase("Student",MODE_PRIVATE,null);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("select * from faculty;", null);
                c.moveToFirst();
                int flag=1;
                if (c != null) {
                    for (int j = 0; j < c.getCount(); j++) {
                        if (c.getString(0).equals(name.getText().toString()) && c.getString(1).equals(pass.getText().toString())) {
                            startActivity(new Intent(getApplicationContext(), Admin.class));
                            flag=0;
                        }
                        c.moveToNext();
                    }
                } if(flag==1){
                    Toast.makeText(Home.this, "INCORRECT USER NAME OR PASSWORD", Toast.LENGTH_SHORT).show();}

            }
        });
           voiceinput=(TextView)findViewById(R.id.textView6);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("Admin") && pass.getText().toString().equals("admin")) {
                    startActivity(new Intent(getApplicationContext(), Facultyins.class));
                }
                else
                {
                    Toast.makeText(Home.this, "INCORRECT USER NAME OR PASSWORD", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag=1;
                Cursor c=db.rawQuery("select * from CSE;",null);
                c.moveToFirst();
                if(c!=null)
                {
                for(int j=0;j<c.getCount();j++) {
                    if (c.getString(0).equals(name.getText().toString()) && c.getString(1).equals(pass.getText().toString())) {
                        Intent i = new Intent(getApplicationContext(), Student.class);
                        i.putExtra("name", name.getText().toString());
                        i.putExtra("pass", pass.getText().toString());
                        startActivity(i);
                        flag=0;
                    }
                    c.moveToNext();
                }}
                if(flag==1){
                Toast.makeText(Home.this, "INCORRECT USER NAME OR PASSWORD", Toast.LENGTH_SHORT).show();}

            }
        });


        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
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
                    checkpass(s1);
                }

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });


        findViewById(R.id.imageView10).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:

                        mspechrec.stopListening();


                        break;
                    case MotionEvent.ACTION_DOWN:

                        mspechrec.startListening(intent);
                        break;
                }

                return false;
            }
        });


    }


    public void checkpass(String s) {
        String n = name.getText().toString();
        String p = pass.getText().toString();
        voiceinput.setText(s);
        if (s.equals("login as teacher") || s.equals("Teacher")) {
            if (n.equals("Admin") && p.equals("admin")) {
                startActivity(new Intent(getApplicationContext(), Admin.class));
            } else {
                Toast.makeText(this, "INCORRECT PASSWORD", Toast.LENGTH_SHORT).show();
            }
        }
        if (s.equals("login as student") || s.equals("Student")) {
            if (n.equals("Student") && p.equals("student")) {
                startActivity(new Intent(getApplicationContext(), Student.class));
            } else {
                Toast.makeText(this, "INCORRECT PASSWORD", Toast.LENGTH_SHORT).show();
            }
        }
        if (s.equals("insert faculty")) {
            if (n.equals("addfac") && p.equals("addfac")) {
                startActivity(new Intent(getApplicationContext(), Facultyins.class));
            } else {
                Toast.makeText(this, "INCORRECT PASSWORD", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                checkpass("Teacher");
                break;

            case R.id.button3:
                checkpass("Student");
                break;
        }
    }







}