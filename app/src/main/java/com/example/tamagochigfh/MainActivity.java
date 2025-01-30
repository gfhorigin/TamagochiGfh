package com.example.tamagochigfh;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tamagochigfh.databinding.ActivityMainBinding;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Hero hero = new Hero(new ProgressBar[]{ //по какой то причине не робит
            binding.hungryBar,
            binding.happyBar,
            binding.intelligenceDevelopmentBar,
            binding.hairinessBar,
            binding.tirednessBar,
            binding.moneyBar,
            binding.stressBar,
            binding.immunityBar,
            binding.nerdinessBar
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.heroImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.heroImage.setRotation(binding.heroImage.getRotation()+25f);

            }

        });
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(hero.isAlive()){
                    Log.d("Thread", "YES");
                    hero.update();

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    runOnUiThread(()->{
                        //for (Hero.Property property : hero.getPropertys()) {
                      //      property.getProgressBar().setProgress((int)property.getValue());
                       // }
                    });
                }

            }
        });

       thread.start();

    }

}