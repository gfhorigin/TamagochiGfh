package com.example.tamagochigfh.mainActivity;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tamagochigfh.DB.HeroDB;
import com.example.tamagochigfh.DB.HeroDao;

import java.util.Objects;

public class MainActivityViewModel extends ViewModel {

    private final static int HUGRY_ID =1 ;
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
    private boolean prop_update = false;

    public LiveData<Class> getChangeActivity(){

        return  nextActivity;
    }
    public void setHero(Hero hero){
        this.hero.setValue(hero);
    }
    public LiveData<Hero> getObserveHero() {
        return hero;
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
    public void updateProperty(int id, int value){
        Hero currentHero = hero.getValue();
        if (currentHero == null) {
            Log.e("updateProperty", "Hero is null!");
            return;
        }

        Log.d("property_update", "ID: " + id + " | Value: " + value);

        float newValue;

        switch (id){
            case HUGRY_ID:
                Log.d("propety set", id+" |"+ value);
                newValue = currentHero.getHungry() + value;
                currentHero.setHungry(newValue);
                break;
        }
        currentHero.syncPropertiesFromColumns();
        hero.setValue(currentHero);
        prop_update = true;
    }

    public void heroThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!prop_update){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Log.d("TEEEEEEEEEEEEEEEEEEEES","----------------------------------------------");
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
