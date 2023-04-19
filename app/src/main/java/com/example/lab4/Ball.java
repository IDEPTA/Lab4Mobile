package com.example.lab4;

public class Ball {
    private float x, y; // координаты шара
    private float vx, vy; // скорость шара

    public Ball(float x, float y) {
        this.x = x;
        this.y = y;
        this.vx = 1000;// начальная скорость по оси x
        this.vy = 1000;
    }

    public float getX(){
        float result = this.x;
        return result;
    }
    public float getY(){
        float result = this.y;
        return result;
    }
    public void update(float dt) {
        x += vx * dt;
        y += vy * dt;
    }

    public void reflectX() {
        vx = -vx;
    }

    public void reflectY() {
        vy = -vy;
    }

}
