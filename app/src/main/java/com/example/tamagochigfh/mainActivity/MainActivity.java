package com.example.tamagochigfh.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tamagochigfh.R;
import com.example.tamagochigfh.databinding.ActivityMainBinding;

import java.util.Objects;

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

        visibleThread();
        mainActivityViewModel.hero.observe(this, hero -> {
                setProgressBar();
        });

        mainActivityViewModel.getChangeActivity().observe(this, value->{
            changeActivity(value);
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

    public void changeActivity(Class nextActivity){
        Intent intent = new Intent(MainActivity.this, nextActivity);
        // Запускаем новую активность
        startActivity(intent);
    }

}