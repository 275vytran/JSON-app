package com.vytran.javajson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//Jsonlint.com to check valid or not
//myjson.com to upload json

public class MainActivity extends AppCompatActivity {

    public static TextView textView;
    public static EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void get(View v) {
        FetchData fetch = new FetchData();
        fetch.execute();
    }

}
