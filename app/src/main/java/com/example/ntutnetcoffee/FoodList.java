package com.example.ntutnetcoffee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodList extends AppCompatActivity {

    private Button btn_Menu,btn_Pay,btn_Delete;
    private TextView TextView2,TextView4,TextView5,TextView6,tv_total;
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        btn_Menu = findViewById(R.id.btn_Menu);
        btn_Pay = findViewById(R.id.btn_Pay);
        btn_Delete = findViewById(R.id.btn_Delete);
        TextView2 = findViewById(R.id.TextView2);
        TextView4= findViewById(R.id.TextView4);
        TextView5= findViewById(R.id.TextView5);
        TextView6= findViewById(R.id.TextView6);
        tv_total= findViewById(R.id.tv_total);

        btn_Menu.setOnClickListener(new View.OnClickListener() {   //按鈕返回菜單
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(FoodList.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(FoodList.this)
                        .setTitle("確認視窗")
                        .setMessage("確定要送出菜單了嗎?")
                        .setPositiveButton("確定", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast toast = Toast.makeText(FoodList.this, "菜單已送出!",
                                                Toast.LENGTH_LONG);
                                        toast.show();

                                    }
                                })
                        .setNegativeButton("取消", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                        .show();
            }
        });
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> food = new ArrayList<String>();
                food.add("炒飯"); food.add("燒鴨飯");food.add("咖哩飯");food.add("雞肉飯");
                food.add("叉燒飯");food.add("燒雞飯");food.add("鰻魚飯");food.add("牛肉麵");
                food.add("湯麵");  food.add("炒麵");food.add("烏龍麵");food.add("番茄肉醬");
                food.add("青醬雞肉");food.add("白醬蘑菇");food.add("草莓蛋糕");food.add("巧克力蛋糕");
                food.add("珍珠奶茶");food.add("咖啡");

                Value v = (Value)getApplicationContext();
                v.setNumber(0); v.setNumber1(0); v.setNumber2(0); v.setNumber3(0); v.setNumber4(0); v.setNumber5(0); v.setNumber6(0);
                v.setNumber7(0);v.setNumber8(0); v.setNumber9(0); v.setNumber10(0); v.setNumber11(0); v.setNumber12(0); v.setNumber13(0);
                v.setNumber14(0); v.setNumber15(0); v.setNumber16(0);v.setNumber17(0);

                TextView4.setText(food.get(0)+"              "+v.getNumber() +"\n"+food.get(1)+"          "+v.getNumber1() +"\n"+food.get(2)+"          "+v.getNumber2()
                        +"\n"+food.get(3)+"          "+v.getNumber3()+"\n"+food.get(4)+"          "+v.getNumber4()+"\n"+food.get(5)+"          "+v.getNumber5()+"\n"+food.get(6)+"          "+v.getNumber6()+"\n"
                        +food.get(7)+"          "+v.getNumber7() +"\n"+food.get(8)+"              "+v.getNumber8()+"\n"+food.get(9)+"              "+v.getNumber9()+"\n"+food.get(10)+"          "+v.getNumber10()+"\n");
                TextView6.setText(food.get(11)+"            "+v.getNumber11()+"\n"+food.get(12)+"            "+v.getNumber12()+"\n"
                        +food.get(13)+"            "+v.getNumber13()+"\n"+food.get(14)+"            "+v.getNumber14()+"\n"+
                        food.get(15)+"        "+v.getNumber15()+"\n"+food.get(16)+"            "+v.getNumber16()+"\n"+
                        food.get(17)+"                    "+v.getNumber17());
                tv_total.setText("總金額:0");

            }
        });
        ArrayList<String> food = new ArrayList<String>();
        food.add("炒飯");food.add("燒鴨飯");food.add("咖哩飯");food.add("雞肉飯");
        food.add("叉燒飯");food.add("燒雞飯");food.add("鰻魚飯");food.add("牛肉麵");
        food.add("湯麵");food.add("炒麵");food.add("烏龍麵");food.add("番茄肉醬");
        food.add("青醬雞肉");food.add("白醬蘑菇");food.add("草莓蛋糕");food.add("巧克力蛋糕");
        food.add("珍珠奶茶");food.add("咖啡");

        Value v = (Value) getApplicationContext();
        TextView4.setText(food.get(0)+"              "+v.getNumber() +"\n"+food.get(1)+"          "+v.getNumber1() +"\n"+food.get(2)+"          "+v.getNumber2()
                +"\n"+food.get(3)+"          "+v.getNumber3()+"\n"+food.get(4)+"          "+v.getNumber4()+"\n"+food.get(5)+"          "+v.getNumber5()+"\n"+food.get(6)+"          "+v.getNumber6()+"\n"
                +food.get(7)+"          "+v.getNumber7() +"\n"+food.get(8)+"              "+v.getNumber8()+"\n"+food.get(9)+"              "+v.getNumber9()+"\n"+food.get(10)+"          "+v.getNumber10()+"\n");

        TextView6.setText(food.get(11)+"            "+v.getNumber11()+"\n"+food.get(12)+"            "+v.getNumber12()+"\n"
                +food.get(13)+"            "+v.getNumber13()+"\n"+food.get(14)+"            "+v.getNumber14()+"\n"+
                food.get(15)+"        "+v.getNumber15()+"\n"+food.get(16)+"            "+v.getNumber16()+"\n"+
                food.get(17)+"                    "+v.getNumber17());

        total=60*v.getNumber()+80*v.getNumber1()+70*v.getNumber2()+60*v.getNumber3()+80*v.getNumber4()+80*v.getNumber5()+100*v.getNumber6()+90*v.getNumber7()
                +50*v.getNumber8()+60*v.getNumber9()+80*v.getNumber10()+85*v.getNumber11()+85*v.getNumber12()+85*v.getNumber13()
                +40*v.getNumber14()+40*v.getNumber15()+50*v.getNumber16()+60*v.getNumber17();

        tv_total.setText("總金額:"+total);
    }
}