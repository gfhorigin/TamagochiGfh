package com.example.tamagochigfh.food;

import com.example.tamagochigfh.R;

import java.util.Random;

public class Food {
    private float x;
    private float y;
    private float liveTime;
    private float fallSpeed;

    public Food(int containerWidth, int containerHeight, int objectWidth, int objectHeight ) {
        x = new Random().nextInt(containerWidth-objectWidth);
        y = new Random().nextInt(containerHeight-objectHeight);
        liveTime = new Random().nextInt(5)+3;
        fallSpeed = new Random().nextInt(10);
    }

    public float getFallSpeed() {
        return fallSpeed;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(float liveTime) {
        this.liveTime = liveTime;
    }
}
