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

public class MainActivity_order_main extends AppCompatActivity {

    class Data{
        int photo;
        String name;

    }

    public class  MyAdapter extends BaseAdapter {
        private MainActivity_order_main.Data[] data ;
        private int view ;
        public MyAdapter(MainActivity_order_main.Data[] data, int view){
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
            //covertView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //covertView.setLayoutParams(new GridView.LayoutParams(320,240));
            return covertView;
        }
    }

    int price;
    String[] foodNameArray = new String[]{"炒飯","燒鴨飯","咖哩飯","雞肉飯","叉燒飯","燒雞飯","鰻魚飯"
    };

    int[] foodPhotoIdArray = new int[]{
            R.drawable.rice,R.drawable.rice1,R.drawable.rice2,R.drawable.rice3,
            R.drawable.rice4,R.drawable.rice5,R.drawable.rice6};
    private ImageView imgShow;
    private TextView textView1,tv_num;
    private ImageButton ImgBtn_plus,ImgBtn_sub;
    private Button btn_AddFood,btn_FoodList,btn_DrinkList,btn_NoodleList,btn_RiceList;
    int num,image;
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
        btn_DrinkList=findViewById(R.id.btn_DrinkList);
        btn_NoodleList=findViewById(R.id.btn_NoodleList);
        btn_RiceList=findViewById(R.id.btn_RiceList);


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
                intent.setClass(MainActivity_order_main.this ,FoodList.class);
                startActivity(intent);
            }
        });

        btn_DrinkList.setOnClickListener(new View.OnClickListener() {   //按鈕換頁
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(MainActivity_order_main.this ,DrinkList.class);
                startActivity(intent);
            }
        });
        btn_NoodleList.setOnClickListener(new View.OnClickListener() {   //按鈕換頁
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(MainActivity_order_main.this ,NoodleList.class);
                startActivity(intent);
            }
        });

        btn_AddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(MainActivity_order_main.this ,FoodList.class);
                if(num==0){
                    Value v = (Value)getApplicationContext();
                    v.setNumber(0);
                }
                if(image==0&&num>=0){
                    Value v = (Value)getApplicationContext();

                    v.setNumber(num);


                }
                else if(image==1&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber1(num);

                }
                else if(image==2&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber2(num);

                }
                else if(image==3&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber3(num);

                }
                else if(image==4&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber4(num);

                }
                else if(image==5&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber5(num);

                }
                else if(image==6&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber6(num);

                }

                Toast toast=Toast.makeText(MainActivity_order_main.this,"餐點已加入點餐籃!",
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
                        textView1.setText("餐點名稱:炒飯\n餐點價格:60元\n食材:米飯、豬肉絲、洋蔥、\n高麗菜、雞蛋");
                        num=0;
                        tv_num.setText(" "+num);
                        image=0;
                        break;
                    case 1:
                        textView1.setText("餐點名稱:燒鴨飯\n餐點價格:80元\n食材:米飯、鴨肉、洋蔥、\n芥藍菜、雞蛋");
                        num=0;
                        tv_num.setText(" "+num);
                        image=1;
                        break;
                    case 2:
                        textView1.setText("餐點名稱:咖哩飯\n餐點價格:70元\n食材:米飯、豬肉切丁、洋蔥、\n胡蘿蔔、馬鈴薯");
                        num=0;
                        tv_num.setText(" "+num);
                        image=2;
                        break;
                    case 3:
                        textView1.setText("餐點名稱:雞肉飯\n餐點價格:60元\n食材:米飯、雞肉、小黃瓜、\n紅蔥頭");
                        num=0;
                        tv_num.setText(" "+num);
                        image=3;
                        break;
                    case 4:
                        textView1.setText("餐點名稱:叉燒飯\n餐點價格:80元\n食材:米飯、五花肉、小黃瓜、\n高麗菜、雞蛋");
                        num=0;
                        tv_num.setText(" "+num);
                        image=4;
                        break;
                    case 5:
                        textView1.setText("餐點名稱:燒雞飯\n餐點價格:80元\n食材:米飯、雞肉、洋蔥、\n花椰菜、雞蛋");
                        num=0;
                        tv_num.setText(" "+num);
                        image=5;
                        break;

                    case 6:
                        textView1.setText("餐點名稱:鰻魚飯\n餐點價格:100元\n食材:米飯、鰻魚、洋蔥、\n蔥花、雞蛋");
                        num=0;
                        tv_num.setText(" "+num);
                        image=6;
                        break;

                }

            }
        });

    }
}