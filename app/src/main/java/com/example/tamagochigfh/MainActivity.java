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
import androidx.fragment.app.FragmentActivity;

import com.example.tamagochigfh.databinding.ActivityMainBinding;
import com.example.tamagochigfh.databinding.MinigamesFragmentBinding;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {


    private static ActivityMainBinding binding;

    private Hero hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.heroImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.heroImage.setRotation(binding.heroImage.getRotation() + 25f);

            }

        });
        hero = new Hero(binding.hpBar,
                new ProgressBar[]{
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
        CharacteristicThread();
        MinigamesFragment myFragment = new MinigamesFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, myFragment) // Добавьте фрагмент в контейнер
                .addToBackStack(null) // Добавьте транзакцию в стек (опционально)
                .commit();


        binding.minigamesBtn.setOnClickListener(v -> {
            binding.fragmentContainer.setVisibility(View.VISIBLE);
        });

    }
    public static ActivityMainBinding getBinding(){
        return  binding;
    }
    void CharacteristicThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (hero.isAlive()) {
                    //  Log.d("Thread", "YES");
                    hero.update();

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    runOnUiThread(() -> {
                        hero.getHp_bar().setProgress((int) hero.getHp());
                        for (Hero.Property property : hero.getPropertys()) {
                            property.getProgressBar().setProgress((int) property.getValue());
                        }
                    });
                }
            }
        });

        thread.start();
}
}