package com.example.ifuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    private Button btn_redirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_redirect = findViewById(R.id.redirectbtn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FuelStationActivity.class);
                startActivity(intent);
            }
        });
    }
}



