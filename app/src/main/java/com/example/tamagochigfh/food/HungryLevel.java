package com.example.tamagochigfh.food;

import java.util.Random;

public class HungryLevel {
    private int points;
    private float time;

    private float fall_speed;

    public HungryLevel() {
        this.points =  new Random().nextInt(2)+2;
        this.time = new Random().nextInt(11)+20;
        this.fall_speed = new Random().nextInt(11)+20f;
    }

    public int getPoints() {
        return points;
    }

    public float getTime() {
        return time;
    }

    public float getFall_speed() {
        return fall_speed;
    }
}
