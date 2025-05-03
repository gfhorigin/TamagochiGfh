package com.example.tamagochigfh.happy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tamagochigfh.imunity.ImunityActivityGame;
import com.example.tamagochigfh.mainActivity.MainActivity;

public class HappyActivityGame extends AppCompatActivity {
    private static final String ID_KEY = "ID";
    private static final String VALUE_KEY = "VALUE";
    private static final String TEXT_KEY = "TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = new Intent(HappyActivityGame.this, MainActivity.class);
        // Запускаем новую активность

        intent.putExtra(ID_KEY, 3);
        intent.putExtra(VALUE_KEY, 80);
        intent.putExtra(TEXT_KEY, "круто!");
        // Hero.getInnstance();
        startActivity(intent);
    }
}