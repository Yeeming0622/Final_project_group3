package com.example.ntutnetcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity_airplane_3 extends AppCompatActivity {

    String scores;//分數的string
    private ListView listView;
    //函式庫宣告
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items = new ArrayList<>();
    private SQLiteDatabase dbrw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_airplane3);
        //宣告layout的元件
        Button btn_back = findViewById(R.id.back);
        Button delete = findViewById(R.id.delete);
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        //函式庫取得
        dbrw = new MyDBHelper(this).getWritableDatabase();
        //取得main2的數值
        Bundle b = getIntent().getExtras().getBundle("key");
        int score = b.getInt("check_point");
        int setting = b.getInt("setting");
        String name = b.getString("name");
        int debug = b.getInt("debug");
        //簡單的分數不變，普通2倍，困難3倍
        if (setting == 0) {
            score = score;
        } else if (setting == 1) {
            score = score * 2;
        } else {
            score = score * 3;
        }
        //當有玩完遊戲，分數就取得，沒有玩完則設為0
        if (debug == 1) {
            scores = Integer.toString(score);
        } else {
            scores = "0";
        }
        //如果使用者名稱一樣，就用update，如果沒有一樣的使用者名稱則新增
        try {
            dbrw.execSQL("INSERT INTO myTable(book, score) VALUES(?,?)"
                    , new Object[]{name, scores});
        } catch (Exception e) {
            dbrw.execSQL("UPDATE myTable SET score = " + scores +
                    " WHERE book LIKE '" + name + "'");
        }
        //顯示出資料庫所有的資料
        Cursor c;
        c = dbrw.rawQuery("SELECT * FROM myTable", null);
        c.moveToFirst();
        items.clear();
        for (int i = 0; i < c.getCount(); i++) {
            items.add("玩家:" + c.getString(0) +
                    "\t\t\t\t 分數:" + c.getString(1));
            c.moveToNext();
        }
        adapter.notifyDataSetChanged();
        //關閉資料庫
        c.close();

        //跳轉業面到第一頁(刪除所有資料)
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c;
                c = dbrw.rawQuery("SELECT * FROM myTable", null);
                c.moveToFirst();
                items.clear();
                //刪除所有名稱(不能刪除null)
                for (int i = 0; i < c.getCount(); i++) {
                    dbrw.execSQL(" DELETE FROM myTable WHERE book LIKE '"
                            + c.getString(0) + "'");
                    c.moveToNext();
                }
                //跳轉業面到第一頁
                startActivityForResult(new Intent(MainActivity_airplane_3.this,
                        MainActivity_airplane_1.class), 1);
            }
        });
        //跳轉業面到第一頁(不刪除資料)
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity_airplane_3.this,
                        MainActivity.class), 1);
            }
        });
    }
}