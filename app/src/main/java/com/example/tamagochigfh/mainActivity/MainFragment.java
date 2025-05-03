package com.example.tamagochigfh.mainActivity;

import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tamagochigfh.DB.HeroDB;
import com.example.tamagochigfh.R;
import com.example.tamagochigfh.databinding.MainFragmentBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainFragment  extends Fragment {
    private MainFragmentBinding mainFragmentBinding;
    private MainActivityViewModel viewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        minigamesOpenBtn();
        hungryBarClick();
        happyBarClick();
        developmentBarClick();

        tirednessBarClick();
        hairinessBarClick();
        stressBarClick();
        immunityBarClick();
        moneyBarClick();
        restartBtn();
        return mainFragmentBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.setHero(generateHero());
        viewModel.heroThread();

        viewModel.mainFragmentVisibility.setValue(View.VISIBLE);
    }

    
    public void minigamesOpenBtn(){
        mainFragmentBinding.minigamesBtn.setOnClickListener(v -> {
            if(viewModel.getHero().isAlive()){
                viewModel.mainFragmentVisibility.setValue(View.GONE);
                viewModel.minigameFragmentVisibility.setValue(View.VISIBLE);
                setText(" ");
            }
        });
    }
    public void restartVisible(){
        mainFragmentBinding.restartBtn.setVisibility(View.VISIBLE);
    }
    public void restartBtn(){
        mainFragmentBinding.restartBtn.setOnClickListener(v->{
            Log.d("restartClick", "CLICKKKKKK");
            // Очищаем БД
            HeroDB.clearDatabase(requireActivity().getApplication());

            // Пересоздаем героя
            viewModel.setHero(generateHero());
            viewModel.heroThread();

            // Обновляем UI
            mainFragmentBinding.textView.setText("");
            mainFragmentBinding.restartBtn.setVisibility(View.GONE);
        });
    }
    public void hungryBarClick(){
        mainFragmentBinding.hungryBar.setOnClickListener(v->{
            Snackbar.make(mainFragmentBinding.getRoot(), R.string.hungry_desc, Snackbar.LENGTH_LONG)
                    .setTextColor(Color.BLACK)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.orange))
                    .show();
        });
    }
    public void happyBarClick(){
        mainFragmentBinding.happyBar.setOnClickListener(v->{
            Snackbar.make(mainFragmentBinding.getRoot(), R.string.happyy_desc, Snackbar.LENGTH_LONG)
                    .setTextColor(Color.BLACK)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.yellow)) 
                    .show();
        });
    }
    public void developmentBarClick(){
        mainFragmentBinding.intelligenceDevelopmentBar.setOnClickListener(v->{
            Snackbar.make(mainFragmentBinding.getRoot(), R.string.development_desc, Snackbar.LENGTH_LONG)
                    .setTextColor(Color.BLACK)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.blue))
                    .show();
        });
    }


    public void tirednessBarClick(){
        mainFragmentBinding.tirednessBar.setOnClickListener(v->{
            Snackbar.make(mainFragmentBinding.getRoot(), R.string.tiredness_desc, Snackbar.LENGTH_LONG)
                    .setTextColor(Color.BLACK)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.green))
                    .show();
        });
    }
    public void hairinessBarClick(){
        mainFragmentBinding.hairinessBar.setOnClickListener(v->{
            Snackbar.make(mainFragmentBinding.getRoot(), R.string.hairiness_desc, Snackbar.LENGTH_LONG)
                    .setTextColor(Color.BLACK)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.brown))
                    .show();
        });
    }
    public void stressBarClick(){
        mainFragmentBinding.stressBar.setOnClickListener(v->{
            Snackbar.make(mainFragmentBinding.getRoot(), R.string.stress_desc, Snackbar.LENGTH_LONG)
                    .setTextColor(Color.BLACK)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.pink))
                    .show();
        });
    }
    public void moneyBarClick(){
        mainFragmentBinding.moneyBar.setOnClickListener(v->{
            Snackbar.make(mainFragmentBinding.getRoot(), R.string.money_desc, Snackbar.LENGTH_LONG)
                    .setTextColor(Color.BLACK)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.lime))
                    .show();
        });
    }
    public void immunityBarClick(){
        mainFragmentBinding.immunityBar.setOnClickListener(v->{
            Snackbar.make(mainFragmentBinding.getRoot(), R.string.immunity_desc, Snackbar.LENGTH_LONG)
                    .setTextColor(Color.BLACK)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.purple))
                    .show();
        });
    }
    public Hero generateHero(){

        final Hero[] hero = new Hero[1];
        final Object lock = new Object();

        new Thread(() -> {
            synchronized(lock) {
                hero[0] = Hero.loadFromDatabase(
                        requireActivity().getApplication(),
                        mainFragmentBinding.hpBar,
                        new ProgressBar[]{
                                mainFragmentBinding.hungryBar,
                                mainFragmentBinding.happyBar,
                                mainFragmentBinding.intelligenceDevelopmentBar,
                                mainFragmentBinding.hairinessBar,
                                mainFragmentBinding.tirednessBar,
                                mainFragmentBinding.moneyBar,
                                mainFragmentBinding.immunityBar,
                                mainFragmentBinding.stressBar,

                           //     mainFragmentBinding.nerdinessBar
                        }
                );
                lock.notifyAll();
            }
        }).start();

        synchronized(lock) {
            try {
                lock.wait(3000); // Ожидаем максимум 3 секунды
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return hero[0] ;
    }

    public void setText(String stringExtra) {
        mainFragmentBinding.textView.setText(stringExtra);
    }
}
