package com.example.tamagochigfh.food;

import java.util.Random;

public class HungryLevel {
    private int points;
    private float time;

    public HungryLevel() {
        this.points =  new Random().nextInt(11)+30;
        this.time = new Random().nextInt(11)+20;
    }

    public int getPoints() {
        return points;
    }

    public float getTime() {
        return time;
    }
}
