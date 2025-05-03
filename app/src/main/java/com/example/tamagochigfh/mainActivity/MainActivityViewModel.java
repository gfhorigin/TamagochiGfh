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
    private static final int IMUNITY_ID =2 ;
    private static final int HAPPY_ID = 3 ;
    private static final int TIREDNESS_ID = 4;
    private static final int DEVELOP_ID = 5 ;
    private static final int STRESS_ID = 6;
    private static final int MONEY_ID = 7;
    public MutableLiveData<Hero> hero  = new MutableLiveData<>();
    public MutableLiveData<Boolean> isUpdate = new MutableLiveData<Boolean>(false);
    public MutableLiveData<Integer> mainFragmentVisibility = new MutableLiveData<>() ;
    public MutableLiveData<Integer> minigameFragmentVisibility = new MutableLiveData<>();
    public MutableLiveData<Class> nextActivity = new MutableLiveData<Class>();
    public MutableLiveData<Boolean> live = new MutableLiveData<>(true);


    public LiveData<Boolean> getLive(){
        return live;
    }
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
                currentHero.setHungry(Math.min(newValue,100));
                break;
            case IMUNITY_ID:
                Log.d("propety set", id+" |"+ value);
                newValue = currentHero.getImmunity() + value;
                currentHero.setImmunity(Math.min(newValue,100));
                break;
            case HAPPY_ID:
                Log.d("propety set", id+" |"+ value);
                newValue = currentHero.getHappy() + value;
                currentHero.setHappy(Math.min(newValue,100));
                break;
            case TIREDNESS_ID:
                Log.d("propety set", id+" |"+ value);
                newValue = currentHero.getTiredness() + value;
                currentHero.setTiredness(Math.min(newValue,100));
                break;
            case DEVELOP_ID:
                Log.d("propety set", id+" |"+ value);
                newValue = currentHero.getIntelligence() + value;
                currentHero.setIntelligence(Math.min(newValue,100));
                break;
            case STRESS_ID:
                Log.d("propety set", id+" |"+ value);
                newValue = currentHero.getStress() + value;
                currentHero.setStress(Math.min(newValue,100));
                break;
            case MONEY_ID:
                Log.d("propety set", id+" |"+ value);
                newValue = currentHero.getMoney() + value;
                currentHero.setMoney(Math.min(newValue,100));
                break;
        }
        currentHero.syncPropertiesFromColumns();

        hero.setValue(currentHero);
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
                live.postValue(hero.getValue().isAlive());
            }
        });
        thread.start();
    }


}
