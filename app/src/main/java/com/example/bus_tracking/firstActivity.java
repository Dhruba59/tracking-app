package com.example.bus_tracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class firstActivity extends AppCompatActivity implements View.OnClickListener {

    Button driverButton , studentsBusButton, teachersBusButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        driverButton = (Button) findViewById(R.id.driverButton);
        studentsBusButton = (Button) findViewById(R.id.studentsBusButton);
        teachersBusButton = (Button) findViewById(R.id.teachersBusButton);

        studentsBusButton.setOnClickListener(this);
        driverButton.setOnClickListener(this);
        teachersBusButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.studentsBusButton){
            Intent intent = new Intent(firstActivity.this, shareAddress.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.teachersBusButton){
            Intent intent = new Intent(firstActivity.this, teachersBus.class);
            startActivity(intent);
        }

        else if (v.getId() == R.id.driverButton){
            Intent intent = new Intent(firstActivity.this, driverClass.class);
            startActivity(intent);
        }
    }
}
//// <meta-data android:name="com.google.android.gms.vision.DEPENDENCIES" android:value="barcode"/>