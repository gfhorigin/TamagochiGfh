package com.example.tamagochigfh.mainActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tamagochigfh.DB.HeroDB;

import java.util.Objects;

public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<Hero> hero  = new MutableLiveData<>();
    public MutableLiveData<Boolean> isUpdate = new MutableLiveData<Boolean>(false);
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
    public LiveData<Boolean> getIsUpdate(){
        return this.isUpdate;
    }
    public void setIsUpdate(){
        this.isUpdate.setValue(false);
    }

    public void heroThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (Objects.requireNonNull(hero.getValue()).isAlive()) {
                    hero.getValue().update();
                    isUpdate.postValue(true);
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
