package com.example.ntutnetcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity_snake_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_snake1);
        EditText tv_id = (EditText) findViewById(R.id.tv_id);
        Button btn_start = (Button) findViewById(R.id.btn_start);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent();
                intent.setClass(MainActivity_snake_1.this, MainActivity_snake_2.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",tv_id.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    // Disable back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}