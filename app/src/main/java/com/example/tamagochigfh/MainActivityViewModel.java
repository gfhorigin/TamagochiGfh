package com.example.tamagochigfh;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tamagochigfh.databinding.ActivityMainBinding;
import com.example.tamagochigfh.databinding.MainFragmentBinding;
import com.example.tamagochigfh.databinding.MinigamesFragmentBinding;

import java.util.Objects;

public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<Hero> hero  = new MutableLiveData<>();
    public MutableLiveData<Integer> mainFragmentVisibility = new MutableLiveData<>() ;
    public MutableLiveData<Integer> minigameFragmentVisibility = new MutableLiveData<>();
    public MutableLiveData<Class> nextActivity = new MutableLiveData<Class>();

    public LiveData<Integer> getMainVisibility(){
        return mainFragmentVisibility;
    }
    public LiveData<Integer> getMinigameVisibility(){
        return minigameFragmentVisibility;
    }

    public LiveData<Class> getChangeActivity(){

        return  nextActivity;
    }
    public void setHero(Hero hero){
        this.hero.setValue(hero);
    }
    public Hero getHero() {
        return hero.getValue();
    }
    public void heroThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (Objects.requireNonNull(hero.getValue()).isAlive()) {
                    //Log.d("Thread", "YES");
                    hero.getValue().update();

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
    }

}
