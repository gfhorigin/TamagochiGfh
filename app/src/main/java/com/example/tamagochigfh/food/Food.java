package com.example.tamagochigfh.food;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tamagochigfh.R;

import java.util.Random;

public class Food {
    private float x;
    private float y = 0;
    private int image;
    private static final int NACHOS_IMAGE= 0;
    private static final int TOMATO_IMAGE= 1;
    private boolean isTasty;


    public Food( int _image) {

        if (_image == NACHOS_IMAGE){
            this.image = R.drawable.nachos;
            this.isTasty = true;
        }
        else if(_image == TOMATO_IMAGE){
            this.image = R.drawable.tomato;
            this.isTasty = false;
        }
    }


    public int getImage() {
        return image;
    }



    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }


    public boolean isTasty() {
        return isTasty;
    }
}
