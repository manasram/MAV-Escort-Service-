package com.example.rajivr.escort_3;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class driverRequestAccept extends Activity {
    private static Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_request_accept);
        b1 = (Button) findViewById(R.id.accept);


        // to redirect to driver maps
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent("com.example.rajivr.escort_3.MapsActivity_driver");
                    startActivity(intent);

                                }
            });


        }
    }

