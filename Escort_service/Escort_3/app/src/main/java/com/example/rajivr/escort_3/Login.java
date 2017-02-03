package com.example.rajivr.escort_3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private static Button b1, b2,b3;
    private EditText ed1, ed2, ed3;
    TextView tx1;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1 = (Button) findViewById(R.id.login_button);
        b2 = (Button) findViewById(R.id.signup_button);

        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText1);
        ed3 = (EditText) findViewById(R.id.editText2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().equals("mxb7169") && ed2.getText().toString().equals("1001227169") &&

                        ed3.getText().toString().equals("123456")) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("com.example.rajivr.escort_3.user");
                    startActivity(intent);
                }

                else if(ed1.getText().toString().equals("rxr3800") && ed2.getText().toString().equals("1001213800") &&

                        ed3.getText().toString().equals("123456")){
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("com.example.rajivr.escort_3.driverRequestAccept");
                    startActivity(intent);
                }

                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }
            }


        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Loading ...", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent("com.example.manasram.escort_tracker.signup_page");
                startActivity(intent2);
            }

        });
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioUser);


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        Toast.makeText(getApplicationContext(), "Driver Profile", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radioButton2:
                        Toast.makeText(getApplicationContext(), "Rider Profile", Toast.LENGTH_SHORT).show();
                        // do operations specific to this selection
                        break;


                }


            }
        });

    }
}

