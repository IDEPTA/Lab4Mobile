package com.example.lab4;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private Ball ball;
    private Paint paint;
    private float dt;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = getHolder();
        holder.addCallback(this);
        ball = new Ball(15, 15);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        dt = 1.0f / 60.0f; // время кадра
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // начать отрисовку
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Canvas canvas = holder.lockCanvas();
                    if (canvas != null) {
                        draw(canvas);
                        update();
                        holder.unlockCanvasAndPost(canvas);
                    }
                    try {
                        Thread.sleep((long) (dt * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // обработать изменение размера экрана
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // остановить отрисовку
    }

    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK);
        // отрисовать стол
        paint.setColor(Color.RED);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        // отрисовать шар
        paint.setColor(Color.BLACK);
        canvas.drawCircle(ball.getX(), ball.getY(), 50, paint);
    }


    public void update() {
        ball.update(dt);
        // проверить столкновение со стенами
        if (ball.getX() < 0 || ball.getX() > getWidth()) {
            ball.reflectX();
        }
        if (ball.getY() < 0 || ball.getY() > getHeight()) {
            ball.reflectY();
        }
    }

}