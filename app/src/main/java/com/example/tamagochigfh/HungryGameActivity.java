package com.example.tamagochigfh;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tamagochigfh.databinding.ActivityMainBinding;
import com.example.tamagochigfh.databinding.HungryGameActivityBinding;

public class HungryGameActivity extends AppCompatActivity {

    private HungryGameActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HungryGameActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
