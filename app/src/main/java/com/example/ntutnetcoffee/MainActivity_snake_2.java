package com.example.ntutnetcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_snake_2 extends AppCompatActivity implements View.OnClickListener {

    private SnakePanelView mSnakePanelView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_snake2);
        Bundle bundle = getIntent().getExtras();
        String tv_id2 = bundle.getString("id" );

        mSnakePanelView = findViewById(R.id.snake_view);
        findViewById(R.id.left_btn).setOnClickListener(this);
        findViewById(R.id.right_btn).setOnClickListener(this);
        findViewById(R.id.top_btn).setOnClickListener(this);
        findViewById(R.id.bottom_btn).setOnClickListener(this);
        findViewById(R.id.start_btn).setOnClickListener(this);
        mSnakePanelView.reSet();

        Button btn_score = (Button) findViewById(R.id.btn_score);
        btn_score.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity_snake_2.this, MainActivity_snake_3.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",tv_id2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.left_btn) {
            mSnakePanelView.setSnakeDirection(GameType.LEFT);
        } else if (v.getId() == R.id.right_btn) {
            mSnakePanelView.setSnakeDirection(GameType.RIGHT);
        } else if (v.getId() == R.id.top_btn) {
            mSnakePanelView.setSnakeDirection(GameType.TOP);
        } else if (v.getId() == R.id.bottom_btn) {
            mSnakePanelView.setSnakeDirection(GameType.BOTTOM);
        } else if (v.getId() == R.id.start_btn) {
            mSnakePanelView.reStartGame();
        }
    }
}