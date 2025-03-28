package com.example.tamagochigfh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tamagochigfh.databinding.ActivityMainBinding;
import com.example.tamagochigfh.databinding.MinigamesFragmentBinding;

import java.util.Objects;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private MainFragment mainFragment;
    private MinigamesFragment minigamesFragment;
    private Handler uiHandler = new Handler(Looper.getMainLooper());
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        minigamesFragment = new MinigamesFragment();
        mainFragment = new MainFragment();

        setupFragments();

        //binding.mainFragmentContainer.setVisibility(View.VISIBLE);


        visibleThread();
        mainActivityViewModel.hero.observe(this, hero -> {
                setProgressBar();
        });

    }

    private void setProgressBar(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mainActivityViewModel.hero.getValue().isAlive()){
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Objects.requireNonNull(mainActivityViewModel.getHero()).getHp_bar()
                                    .setProgress((int) mainActivityViewModel.getHero().getHp());
                            for (Hero.Property property : mainActivityViewModel.getHero().getPropertys()) {
                                property.getProgressBar().setProgress((int) property.getValue());
                            }
                        }
                    });
                }


            }
        }).start();
    }
    private void visibleThread(){

        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                mainActivityViewModel.getMainVisibility().observe(MainActivity.this, v->{
                    binding.mainFragmentContainer.setVisibility(v);
                });
                mainActivityViewModel.minigameFragmentVisibility.observe(MainActivity.this, v->{
                    binding.minigameFragmentContainer.setVisibility(v);
                });
            }
        });
    }

    private void setupFragments() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.minigame_fragment_container, minigamesFragment)
                .add(R.id.main_fragment_container,mainFragment)
                .commit();
    }


//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.minigame_fragment_container, minigamesFragment) // Добавьте фрагмент в контейнер
//                .addToBackStack(null) // Добавьте транзакцию в стек (опционально)
//                .commit();
//        binding.minigameFragmentContainer.setVisibility(View.VISIBLE);
    //            binding.minigameFragmentContainer.setVisibility(View.GONE);
    // binding = ActivityMainBinding.inflate(getLayoutInflater()); ActivityMainBinding.inflate(getLayoutInflater()
//    void CharacteristicThread(){
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (hero.isAlive()) {
//                    //  Log.d("Thread", "YES");
//                    hero.update();
//
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    runOnUiThread(() -> {
//                        hero.getHp_bar().setProgress((int) hero.getHp());
//                        for (Hero.Property property : hero.getPropertys()) {
//                            property.getProgressBar().setProgress((int) property.getValue());
//                        }
//                    });
//                }
//            }
//        });
//
//        thread.start();
//    }
}