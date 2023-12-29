package com.example.ntutnetcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_airplane_1 extends AppCompatActivity {
    private EditText ed_name;//使用者名稱

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_airplane1);
        ed_name = findViewById(R.id.ed_name);
        //三個選擇難度的按鈕
        Button btn_easy = findViewById(R.id.btn_easy);
        Button btn_standard = findViewById(R.id.btn_standard);
        Button btn_hard = findViewById(R.id.btn_hard);
        //使用bundle來傳資料
        Bundle b = new Bundle();
        Intent intent = new Intent(MainActivity_airplane_1.this, MainActivity_airplane_2.class);
        intent.putExtra("key", b);
        //將數值傳到2
        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_name.length() < 1)
                    Toast.makeText(MainActivity_airplane_1.this, "欄位請勿留空",
                            Toast.LENGTH_SHORT).show();
                else {
                    b.putString("name", ed_name.getText().toString());
                    b.putInt("debug", 0);
                    b.putInt("setting", 0);
                    startActivity(intent);
                }
            }
        });
        btn_standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_name.length() < 1)
                    Toast.makeText(MainActivity_airplane_1.this, "欄位請勿留空",
                            Toast.LENGTH_SHORT).show();
                else {
                    b.putString("name", ed_name.getText().toString());
                    b.putInt("debug", 0);
                    b.putInt("setting", 1);
                    startActivity(intent);
                }
            }
        });
        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_name.length() < 1)
                    Toast.makeText(MainActivity_airplane_1.this, "欄位請勿留空",
                            Toast.LENGTH_SHORT).show();
                else {
                    b.putString("name", ed_name.getText().toString());
                    b.putInt("debug", 0);
                    b.putInt("setting", 2);
                    startActivity(intent);
                }
            }
        });
    }
}