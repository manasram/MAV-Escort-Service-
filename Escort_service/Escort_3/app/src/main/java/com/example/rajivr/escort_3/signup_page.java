package com.example.rajivr.escort_3;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup_page extends Activity {
    private static Button b3;
    private TextView tx1,tx2,tx3,tx4,tx5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        b3 = (Button) findViewById(R.id.button);
        tx1 = (TextView) findViewById(R.id.textView5);
        tx2 = (TextView) findViewById(R.id.textView7);
        tx3 = (TextView) findViewById(R.id.textView9);
        tx1 = (TextView) findViewById(R.id.textView11);
        tx1 = (TextView) findViewById(R.id.textView13);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Successful ... Welcome!", Toast.LENGTH_SHORT).show();


            }

        });
    }

}