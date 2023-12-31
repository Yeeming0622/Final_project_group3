package com.example.ntutnetcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_airplane_2 extends AppCompatActivity implements TKResultListener, RockerTrackListener {

    String name;//使用者名稱
    int debug;//判斷是否有玩完遊戲，還是直接就結算成績
    int setting;//難度選擇(0=簡單，1=普通，2=困難)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_airplane2);
        final com.example.ntutnetcoffee.MoveButton rkv_rocker = findViewById(R.id.rkv_rocker);
        final com.example.ntutnetcoffee.TKView tk_01 = findViewById(R.id.tk_01);
        //顯示頁面
        tk_01.setOnClickListener(v -> tk_01.invalidate());
        tk_01.setTkResultListener(this);
        rkv_rocker.setTrackListener(this);
        //讀取main1的值
        Bundle b = getIntent().getExtras().getBundle("key");
        name = b.getString("name");
        debug = b.getInt("debug");
        setting = b.getInt("setting");
        //將難度的選擇傳進遊戲頁面
        tk_01.setHealthPoint(setting);
        //當一直移動的時候
        tk_01.setOnLongClickListener(v ->
        {
            tk_01.continuedMove();
            return true;
        });
        rkv_rocker.setOnClickListener(v -> rkv_rocker.invalidate());
    }
    @Override
    public void onToLeft() {
        ((TKView) this.findViewById(R.id.tk_01)).stepsLeft();
    }
    //向右移動
    @Override
    public void onToRight() {
        ((TKView) this.findViewById(R.id.tk_01)).stepsRight();
    }
    //停止移動
    @Override
    public void onStopMove() {
        ((TKView) this.findViewById(R.id.tk_01)).stopMove();
    }

    @Override
    public void onScoreListener(int score) {
        //將數值傳到main3
        Bundle b = new Bundle();
        Intent intent = new Intent(MainActivity_airplane_2.this, MainActivity_airplane_3.class);
        intent.putExtra("key", b);
        //宣告button查看排名跟取得分數
        Button check_score = findViewById(R.id.check_score);
        TextView tv_score = findViewById(R.id.tv_score);
        //在main2顯示即時的分數
        String s = Integer.toString(score);
        tv_score.setText(s);
        check_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //當有玩完遊戲，debug就為1
                debug = 1;
                b.putInt("check_point", score);
                b.putInt("debug", debug);
                b.putString("name", name);
                b.putInt("setting", setting);
                startActivity(intent);
            }
        });
    }
    //在main2顯示即時的血量
    @Override
    public void onGameOver(int blood) {
        TextView tv_blood = findViewById(R.id.tv_blood);
        String s = Integer.toString(blood);
        tv_blood.setText(s);
    }
}