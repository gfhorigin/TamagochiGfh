package com.example.tamagochigfh;

import android.util.Log;
import android.widget.ProgressBar;

public class Hero {
    private float hp= 100f;
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

    public Hero(ProgressBar[] progressBars) {
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
            this.hp -= (1.5f- (property.getValue()/30));
            if(this.hp >100){
                this.hp = 100f;
            }
            property.setValue(property.getValue()-0.1f);
        }
    }

    public float getHp() {
        return hp;
    }

    public Property[] getPropertys() {
        return propertys;
    }
}
