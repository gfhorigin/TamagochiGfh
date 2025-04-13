package com.example.tamagochigfh.food;

import static java.lang.Thread.sleep;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;

public class HungryGameViewModel extends ViewModel {

    private static final float SPAWN_TIME = 1f;
    private MutableLiveData<Boolean> activity_life =  new MutableLiveData<Boolean>(true);
    private MutableLiveData<Float> time = new MutableLiveData<Float>();
    private int hungry_points = 0;
    public LiveData<Float> getTime() {
        return time;
    }
    private ArrayList<Food> foods = new ArrayList<>();
    private HungryLevel level;
    private MutableLiveData<Food> lastFood = new MutableLiveData<Food>();
    public LiveData<Food> getLastFood(){
        return lastFood;
    }
    public void createLevl(){
        level = new HungryLevel();
        time.setValue(level.getTime());
    }

    public void timerThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (time.getValue()>0){
                    try {

                        sleep(100);
                        time.postValue((float) (Math.round( (time.getValue()-0.1f )*10)/10f));


                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                time.postValue(0f);


            }
        }).start();
    }
    public void spawnThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (time.getValue()>0){
                    try {
                        sleep((long) (1000*SPAWN_TIME));


                        lastFood.postValue(new Food(new Random().nextInt(10)%2));
                        foods.add(lastFood.getValue());

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                activity_life.postValue(false);


            }
        }).start();
    }
    public float getNewPosition(int containerWidth, int objectWidth){
        return new Random().nextInt(containerWidth-objectWidth);
    }

    public LiveData<Boolean> isActivity_life() {
        return activity_life;
    }

    public void subtractHungry_points () {
         hungry_points -= level.getPoints();
    }

    public void addHungry_points () {
        hungry_points += level.getPoints();
    }

    public int getHungry_points() {
        return hungry_points ;
    }

    public float getSpeed(){
        return  level.getFall_speed();
    }
}
