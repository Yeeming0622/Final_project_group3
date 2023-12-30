package com.example.ntutnetcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mAirplane, mSnake, mOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAirplane = findViewById(R.id.Airplane);
        mSnake = findViewById(R.id.Snake);
        mOrder = findViewById(R.id.Order);

        mAirplane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity_airplane_1.class);
                startActivity(intent);
            }
        });

        mSnake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent();
                intent.setClass(MainActivity.this, MainActivity_snake_1.class);
                startActivity(intent);
            }
        });

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent();
                intent.setClass(MainActivity.this, MainActivity_order_main.class);
                startActivity(intent);
            }
        });
    }
}