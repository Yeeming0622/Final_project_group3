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

public class NoodleList extends AppCompatActivity {

    class Data{
        int photo;
        String name;
    }
    public class  MyAdapter extends BaseAdapter {
        private NoodleList.Data[] data ;
        private int view ;
        public MyAdapter(NoodleList.Data[] data, int view){
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

    String[] foodNameArray = new String[]{"牛肉麵","湯麵","炒麵","烏龍麵","番茄肉醬\n義大利麵","青醬雞肉\n義大利麵","白醬蘑菇\n義大利麵"
    };
    int[] foodPhotoIdArray = new int[]{
            R.drawable.noodle1,R.drawable.noodle2,R.drawable.noodle3,R.drawable.noodle4,
            R.drawable.noodle5,R.drawable.noodle6,R.drawable.noodle7};
    private ImageView imgShow;
    private TextView textView1,tv_num;
    private ImageButton ImgBtn_plus,ImgBtn_sub;
    private Button btn_AddFood,btn_FoodList,btn_DrinkList,btn_RiceList;
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
                intent.setClass(NoodleList.this ,FoodList.class);
                startActivity(intent);
            }
        });
        btn_DrinkList.setOnClickListener(new View.OnClickListener() {   //按鈕換頁
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(NoodleList.this ,DrinkList.class);
                startActivity(intent);
            }
        });
        btn_RiceList.setOnClickListener(new View.OnClickListener() {   //按鈕換頁
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(NoodleList.this ,MainActivity_order_main.class);
                startActivity(intent);
            }
        });


        btn_AddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(NoodleList.this ,FoodList.class);
                if(image==0&&num>=0){
                    Value v = (Value)getApplicationContext();

                    v.setNumber7(num);


                }
                else if(image==1&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber8(num);

                }
                else if(image==2&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber9(num);

                }
                else if(image==3&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber10(num);

                }
                else if(image==4&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber11(num);

                }
                else if(image==5&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber12(num);

                }
                else if(image==6&&num>=0){

                    Value v = (Value)getApplicationContext();
                    v.setNumber13(num);

                }
                Toast toast=Toast.makeText(NoodleList.this,"餐點已加入點餐籃!",
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
                        textView1.setText("餐點名稱:牛肉麵\n餐點價格:90元\n食材:白麵條、牛腩肉、洋蔥、\n胡蘿蔔、青江菜");
                        num=0;
                        tv_num.setText(" "+num);
                        image=0;
                        break;
                    case 1:
                        textView1.setText("餐點名稱:湯麵\n餐點價格:50元\n食材:黃麵條、豬肉片、青蔥、\n雞蛋");
                        num=0;
                        tv_num.setText(" "+num);
                        image=1;
                        break;
                    case 2:
                        textView1.setText("餐點名稱:炒麵\n餐點價格:60元\n食材:黃麵條、豬肉、洋蔥、\n胡蘿蔔、雞蛋");
                        num=0;
                        tv_num.setText(" "+num);
                        image=2;
                        break;
                    case 3:
                        textView1.setText("餐點名稱:烏龍麵\n餐點價格:80元\n食材:烏龍麵、豬肉、青蔥、\n香菇、雞蛋");
                        num=0;
                        tv_num.setText(" "+num);
                        image=3;
                        break;
                    case 4:
                        textView1.setText("餐點名稱:番茄肉醬義大利麵\n餐點價格:85元\n食材:義大利麵條、番茄、\n洋蔥、香菇、蒜末");
                        num=0;
                        tv_num.setText(" "+num);
                        image=4;
                        break;
                    case 5:
                        textView1.setText("餐點名稱:青醬雞肉義大利麵\n餐點價格:85元\n食材:義大利麵條、雞胸肉、\n洋蔥、白酒、蒜末、羅勒葉");
                        num=0;
                        tv_num.setText(" "+num);
                        image=5;
                        break;

                    case 6:
                        textView1.setText("餐點名稱:白醬蘑菇義大利麵\n餐點價格:85元\n食材:義大利麵條、蘑菇、\n洋蔥、牛奶、蒜末、羅勒葉");
                        num=0;
                        tv_num.setText(" "+num);
                        image=6;
                        break;

                }
            }
        });
    }
}