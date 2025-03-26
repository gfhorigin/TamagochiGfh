package com.example.tamagochigfh;

import android.util.Log;
import android.widget.ProgressBar;

public class Hero {
    private static final float DELTA_PROPERTY_POINT = 0.1f;
    private static final float DELTA_HP_POINT = 1.5f;
    private static final float MAX_HP = 100f;
    private float hp= MAX_HP;
    private ProgressBar hp_bar;
    private Property[] propertys= new Property[]{
            new Property("hungry"),
            new Property("happy"),
            new Property("intelligence_development"),
            new Property("nerdiness"),
            new Property("tiredness"),
            new Property("hairiness"),
            new Property("stress"),
            new Property("immunity"),
            new Property("money")
    };

    class Property{
        private String name;
        private float value=100f;
        private ProgressBar progressBar;


        // -----------к каждому привязать фрагмент со своей мини игрой----------------

        public Property(String name) {
            this.name = name;
        }
        public void setProgressBar(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        public ProgressBar getProgressBar() {
            return progressBar;
        }


        public String getName() {
            return name;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }

    public Hero(ProgressBar hpBar,ProgressBar[] progressBars) {
        hp_bar = hpBar;
        for (int i = 0 ; i<progressBars.length; i++){
            if (progressBars[i] != null){
                propertys[i].setProgressBar(progressBars[i]);
            }

        }
    }

    public boolean isAlive(){
        if(hp>0) {
            return true;
        }
        return false;
    }

    public void update(){
        for (Property property : propertys) {
            this.hp -= (DELTA_HP_POINT - (property.getValue()/30));
            if(this.hp >MAX_HP){
                this.hp = MAX_HP;
            }
            if (property.getValue() > 0){
                property.setValue(property.getValue()-DELTA_PROPERTY_POINT);
            }
        }
    }

    public float getHp() {
        return hp;
    }

    public ProgressBar getHp_bar(){
        return hp_bar;
    }

    public Property[] getPropertys() {
        return propertys;
    }
}
