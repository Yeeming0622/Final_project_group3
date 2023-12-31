package com.example.ntutnetcoffee;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TKView extends View {
    private Paint paint;//繪圖
    private final Random random = new Random();//變數
    private int bombSpeed = 20;//子彈速度
    private int enemySpeed = 10;//敵人速度
    private int plantSpeed = 10;//飛機速度
    private int width;
    private int height;
    private boolean isStart = true;//判斷是否有在遊玩
    //宣告物件
    private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.tk);
    private Bitmap bomb = BitmapFactory.decodeResource(getResources(), R.mipmap.bomb);
    private Bitmap enemy = BitmapFactory.decodeResource(getResources(), R.mipmap.enemy);
    private Bitmap blast = BitmapFactory.decodeResource(getResources(), R.mipmap.blast);
    //飛機的原始位置
    private int tkX;
    private int tkY;
    private boolean isMove = true;//飛機是否在移動
    private boolean Move_init = true;//用來初始化飛機一開始的位置
    private float touchX;//按鈕的數值
    private ArrayList<Point> points = new ArrayList<Point>();
    private ArrayList<Point> enemys = new ArrayList<Point>();//blast
    private ArrayList<Point> blasts = new ArrayList<Point>();
    private int index;
    private static int score = 0;
    private int enemey_num = 15;
    private int blood = 5;
    private int blood_level;
    private int currentSetting = 1;
    private TKResultListener tkResultListener;
    public int bomb_num = 10;
    SoundPool soundPool = new SoundPool(32, AudioManager.STREAM_MUSIC,0);
    AssetManager assetManager = getContext().getAssets();
    AssetFileDescriptor shoot = assetManager.openFd("s.ogg");
    AssetFileDescriptor boom = assetManager.openFd("b.ogg");
    int id1 = soundPool.load(shoot,1);
    int id2 = soundPool.load(boom, 1);
    public TKView(Context context, @Nullable AttributeSet attrs) throws IOException {
        super(context, attrs);
        init();
    }
    private void init() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#ff4358"));
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(5);
            }
        }, 0, 40);
    }
    public void setTkResultListener(TKResultListener tkResultListener) {
        this.tkResultListener = tkResultListener;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //取得頁面大小
        width = getWidth();
        height = getHeight();
        //初始化飛機位置
        if (isStart) {
            if (Move_init) {
                Move_init = false;
                tkX = width / 2;
                tkY = height - 20;
            }
            //畫飛機
            canvas.drawBitmap(bitmap, tkX - 24, tkY - 50, paint);
            //子彈
            for (int i = 0; i < points.size(); i++) {
                soundPool.play(id1,1.0f,1.0f,0,0,1);

                canvas.drawBitmap(bomb, points.get(i).x, points.get(i).y, paint);
                soundPool.unload(id2);
                soundPool.release();
            }
            //敵人
            for (int i = 0; i < enemys.size(); i++) {
                canvas.drawBitmap(enemy, enemys.get(i).x - 24, enemys.get(i).y, paint);
            }
            //當子彈打到敵人，就爆炸，並且移除
            for (int i = 0; i < blasts.size(); i++) {
                soundPool.play(id2,1.0f,1.0f,0,0,1);

                canvas.drawBitmap(blast, blasts.get(i).x - 64, blasts.get(i).y, paint);
                blasts.remove(i);
                score++;
                i--;
                soundPool.unload(id2);
                soundPool.release();
            }
            //回傳即時的血量和分數
            if (tkResultListener != null) {
                tkResultListener.onScoreListener(score);
            }
            if (tkResultListener != null) {
                tkResultListener.onGameOver(blood);
            }
        } else {
            //如果結束遊戲，就顯示
            showMessageDialog();
        }
    }
    //向左移動
    public void stepsLeft() {
        if (!isMove || touchX != 0) {
            handler.removeMessages(1);
            isMove = true;
            touchX = 0;
            handler.sendEmptyMessage(1);
        }
    }
    private void moveToLeft() {
        if (tkX > plantSpeed && tkX > touchX) {
            tkX -= plantSpeed;
            invalidate();
        }
    }
    //向右移動
    public void stepsRight() {
        if (!isMove || touchX != width) {
            handler.removeMessages(1);
            isMove = true;
            touchX = width;
            handler.sendEmptyMessage(1);
        }
    }
    private void moveToRight() {
        if (tkX < width - plantSpeed && tkX < touchX) {
            tkX += plantSpeed;
            invalidate();
        }
    }
    //如果血量=0
    private void gameover(int num) {
        if (num < 0) {
            isStart = false;
            //將頁面上的敵人和子彈都去除
            for (int i = 0; i < enemys.size(); i++) {
                if (enemys.get(i).y >= 0) {
                    enemys.remove(i);
                    i--;
                }
            }
            for (int j = 0; j < points.size(); j++) {
                if (points.get(j).y >= 0)
                    points.remove(j);
                j--;
            }
        }
    }
    //跳出顯示頁面
    private void showMessageDialog() {
        post(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(getContext()).setMessage("Game " + "Over!")
                        .setCancelable(false)
                        .setPositiveButton("重新開始", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                reStartGame();
                            }
                        })
                        .setNegativeButton("結算成績", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                score = 0;
                            }
                        })
                        .create()
                        .show();
            }
        });
    }
    //因難度選擇數值
    public void setHealthPoint(int idx) {
        currentSetting = idx;
        setHealthPoint();
    }
    private void setHealthPoint() {
        switch (currentSetting) {
            case 0:
                blood = 10;
                blood_level = blood;
                bomb_num = 1;
                enemey_num = 15;
                break;
            case 1:
                blood = 5;
                blood_level = blood;
                bomb_num = 10;
                enemey_num = 7;
                break;
            case 2:
                blood = 3;
                blood_level = blood;
                bomb_num = 10;
                enemey_num = 3;
                break;
        }
    }
    //停止移動
    public void stopMove() {
        isMove = false;
    }
    //持續移動
    public void continuedMove() {
        handler.sendEmptyMessage(1);
    }
    //重新遊玩一次
    public void reStartGame() {
        blood = blood_level;//將同難度的血量重新宣告
        score = 0;
        tkX = width / 2 - 32;
        tkY = height - 84;
        isStart = true;
    }
    //非同步執行
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (isStart) {
                switch (message.what) {
                    //向左向右移動
                    case 1:
                        if (touchX > tkX) {
                            moveToRight();
                        } else {
                            moveToLeft();
                        }
                        if (isMove) {
                            handler.sendEmptyMessageDelayed(1, 10);
                        }
                        break;
                    case 5:
                        //敵人下降
                        for (int i = 0; i < enemys.size(); i++) {
                            int x = enemys.get(i).x;
                            int y = enemys.get(i).y + enemySpeed;
                            enemys.get(i).x = x;
                            enemys.get(i).y = y;
                            if (enemys.get(i).y >= height) {
                                enemys.remove(i);
                                blood--;
                                gameover(blood);
                                i--;
                            } else {
                                for (int j = 0; j < points.size(); j++) {
                                    Point p = getCollidePoint(x, y, points.get(j).x, points.get(j).y);
                                    if (p != null) {
                                        blasts.add(new Point((x + points.get(j).x) / 2, (y + points.get(j).y) / 2));
                                        points.remove(j);
                                        j--;
                                        enemys.remove(i);
                                        i--;
                                        break;
                                    }
                                }
                            }
                        }
                        //子彈上升
                        for (int i = 0; i < points.size(); i++) {
                            Point p = points.get(i);
                            points.get(i).x = p.x;
                            points.get(i).y = p.y - bombSpeed;
                            if (points.get(i).y <= 0) {
                                points.remove(i);
                                i--;
                            }
                        }
                        index++;
                        //子彈數量
                        if (index % bomb_num == 0) {
                            points.add(new Point(tkX, (tkY - 60)));
                        }
                        //敵人產生
                        if (index % enemey_num == 0) {
                            int x = random.nextInt(width);
                            enemys.add(new Point(x, 0));
                        }
                        invalidate();
                        break;
                }
            }
            return false;
        }
    });
    //攻擊打到敵人的處理
    public Point getCollidePoint(int x, int y, int tx, int ty) {
        //碰撞點初始為0
        Point p = null;
        //得到第一個碰撞的RectF
        RectF rectF1 = new RectF(x, y, x + 24f, y + 24f);
        //得到第二個碰撞的RectF
        RectF rectF2 = new RectF(tx, ty, tx + 24f, ty + 24f);
        //新的RectF
        RectF rectF = new RectF();
        //通過setIntersect()得到兩個是否有相交
        boolean isIntersect = rectF.setIntersect(rectF1, rectF2);
        //如果两相交
        if (isIntersect) {
            //得到交点
            p = new Point(Math.round(rectF.centerX()), Math.round(rectF.centerY()));
        }
        //返回交点
        return p;
    }

}
