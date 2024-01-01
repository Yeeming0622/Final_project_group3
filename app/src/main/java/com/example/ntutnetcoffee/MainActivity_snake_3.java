package com.example.ntutnetcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_snake_3 extends AppCompatActivity implements Listener{

    @Override
    public void send(String s) {
        Log.e("tag: ", s);
        TextView tv_score = findViewById(R.id.tv_score);
        tv_score.setText(String.format("%s", s));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_snake3);

        Bundle bundle = getIntent().getExtras();
        String tv_id2 = bundle.getString("id" );
        TextView tv_id3 = (TextView)findViewById(R.id.tv_id3);
        tv_id3.setText(tv_id2);

        SnakePanelView.Data data = new SnakePanelView.Data(this);
        data.sends();

        Button btn_re = findViewById(R.id.btn_re);

        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity_snake_3.this, MainActivity_snake_2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}