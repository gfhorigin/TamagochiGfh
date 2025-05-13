package com.example.tamagochigfh.money;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tamagochigfh.mainActivity.MainActivity;

public class MoneyGameActivity extends AppCompatActivity {
    private static final String ID_KEY = "ID";
    private static final String VALUE_KEY = "VALUE";
    private static final String TEXT_KEY = "TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        // Запускаем новую активность

        intent.putExtra(ID_KEY, 7);
        intent.putExtra(VALUE_KEY, 80);
        intent.putExtra(TEXT_KEY, "еееее!");
        // Hero.getInnstance();
        startActivity(intent);
    }
}
