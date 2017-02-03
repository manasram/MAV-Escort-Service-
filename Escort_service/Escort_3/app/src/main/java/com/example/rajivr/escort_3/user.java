package com.example.rajivr.escort_3;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.example.rajivr.escort_3.R.id.imageButton;

public class user extends Activity {
    private static ImageButton b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        b = (ImageButton) findViewById(imageButton);
        b = (ImageButton) findViewById(R.id.imageButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Opening Map ...", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent("com.example.rajivr.escort_3.MapsActivity");
                startActivity(intent1);
            }

        });
    }
}
