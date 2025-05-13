package com.example.tamagochigfh.tiredness;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tamagochigfh.mainActivity.MainActivity;

public class TirednessGameActivity extends AppCompatActivity {
    private static final String ID_KEY = "ID";
    private static final String VALUE_KEY = "VALUE";
    private static final String TEXT_KEY = "TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        // Запускаем новую активность

        intent.putExtra(ID_KEY, 4);
        intent.putExtra(VALUE_KEY, 80);
        intent.putExtra(TEXT_KEY, "ухх!");
        // Hero.getInnstance();
        startActivity(intent);
    }
}