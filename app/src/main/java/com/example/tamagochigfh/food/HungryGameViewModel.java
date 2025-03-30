package com.example.tamagochigfh.food;

import static java.lang.Thread.sleep;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HungryGameViewModel extends ViewModel {
    private MutableLiveData<Float> time = new MutableLiveData<Float>();
    private Handler handler = new Handler(Looper.getMainLooper());
    public LiveData<Float> getTime() {
        return time;
    }
    private HungryLevel level;
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

}
