package com.example.ntutnetcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkList extends AppCompatActivity {

    class Data{
        int photo;
        String name;
    }
    public class  MyAdapter extends BaseAdapter {
        private DrinkList.Data[] data ;
        private int view ;
        public MyAdapter(DrinkList.Data[] data, int view){
            this.data=data;
            this.view=view;
        }
        @Override
        public int getCount(){
            return data.length;
        }
        @Override
        public Object getItem(int position){
            return data[position];
        }
        @Override
        public long getItemId(int position){
            return 0;
        }
        @Override
        public View getView(int position, View covertView, ViewGroup parent){

            covertView=getLayoutInflater().inflate(view,parent,false);
            TextView name =covertView.findViewById(R.id.tv_msg);
            name.setText(data[position].name);
            ImageView imageView=covertView.findViewById(R.id.img_photo);
            imageView.setImageResource(data[position].photo);

            return covertView;


        }



    }

    String[] foodNameArray = new String[]{"草莓蛋糕","巧克力蛋糕","珍珠奶茶","咖啡"
    };
    int[] foodPhotoIdArray = new int[]{
            R.drawable.cake1,R.drawable.cake2,R.drawable.drink1,R.drawable.drink2};
    private ImageView imgShow;
    private TextView textView1,tv_num;
    private ImageButton ImgBtn_plus,ImgBtn_sub;
    private Button btn_AddFood,btn_FoodList,btn_RiceList,btn_NoodleList;
    int num=0,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_order_main);
        imgShow=findViewById(R.id.imgShow);
        textView1=findViewById(R.id.textView1);
        ImgBtn_plus=findViewById(R.id.ImgBtn_plus);
        ImgBtn_sub=findViewById(R.id.ImgBtn_sub);
        tv_num=findViewById(R.id.tv_num);
        btn_AddFood=findViewById(R.id.btn_AddFood);
        btn_FoodList=findViewById(R.id.btn_FoodList);
        btn_RiceList=findViewById(R.id.btn_RiceList);
        btn_NoodleList=findViewById(R.id.btn_NoodleList);
        ImgBtn_plus.setOnClickListener(new View.OnClickListener() {  //按鈕增加餐點數量
            @Override
            public void onClick(View view) {
                num++;
                tv_num.setText(" "+num);
            }
        });
        ImgBtn_sub.setOnClickListener(new View.OnClickListener() {    //按鈕減少餐點數量
            @Override
            public void onClick(View view) {
                num--;
                if(num<0)
                    num=0;
                tv_num.setText(" "+num);
            }
        });

        btn_FoodList.setOnClickListener(new View.OnClickListener() {   //按鈕換頁
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(DrinkList.this ,FoodList.class);
                startActivity(intent);
            }
        });
        btn_RiceList.setOnClickListener(new View.OnClickListener() {   //按鈕換頁
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(DrinkList.this ,MainActivity_order_main.class);
                startActivity(intent);
            }
        });
        btn_NoodleList.setOnClickListener(new View.OnClickListener() {   //按鈕換頁
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(DrinkList.this ,NoodleList.class);
                startActivity(intent);
            }
        });
        btn_AddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(DrinkList.this ,FoodList.class);
                if(image==0&&num>=0){
                    Value v = (Value)getApplicationContext();
                    v.setNumber14(num);
                }
                else if(image==1&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber15(num);

                }
                else if(image==2&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber16(num);

                }
                if(image==3&&num>=0) {

                    Value v = (Value) getApplicationContext();
                    v.setNumber17(num);
                }

                Toast toast=Toast.makeText(DrinkList.this,"餐點已加入點餐籃!",
                        Toast.LENGTH_SHORT);
                toast.show();

            }
        });


        Data[] foodData = new Data[foodNameArray.length];
        for (int i =0 ; i < foodData.length; i++) {
            foodData[i] = new Data();
            foodData[i].name = foodNameArray[i];
            foodData[i].photo = foodPhotoIdArray[i];
        }
        MyAdapter adapter =new MyAdapter(foodData,R.layout.adapter_vertical);
        GridView gridView = findViewById(R.id.GridView);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(2);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent,View v,
                                    int position,long id){
                imgShow.setImageResource(foodPhotoIdArray[position]);
                ImgBtn_plus.setVisibility(View.VISIBLE);
                ImgBtn_sub.setVisibility(View.VISIBLE);
                tv_num.setVisibility(View.VISIBLE);
                btn_AddFood.setVisibility(View.VISIBLE);
                switch (position){
                    case 0:
                        textView1.setText("餐點名稱:草莓蛋糕\n餐點價格:40元");
                        num=0;
                        tv_num.setText(" "+num);
                        image=0;
                        break;
                    case 1:
                        textView1.setText("餐點名稱:巧克力蛋糕\n餐點價格:40元");
                        num=0;
                        tv_num.setText(" "+num);
                        image=1;
                        break;
                    case 2:
                        textView1.setText("餐點名稱:珍珠奶茶\n餐點價格:50元");
                        num=0;
                        tv_num.setText(" "+num);
                        image=2;
                        break;
                    case 3:
                        textView1.setText("餐點名稱:咖啡\n餐點價格:60元");
                        num=0;
                        tv_num.setText(" "+num);
                        image=3;
                        break;
                }
            }
        });
    }
}