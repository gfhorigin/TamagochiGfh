package com.example.tamagochigfh;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tamagochigfh.databinding.MainFragmentBinding;

public class MainFragment  extends Fragment {
    private MainFragmentBinding mainFragmentBinding;
    private MainActivityViewModel viewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        minigamesOpenBtn();
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
            viewModel.mainFragmentVisibility.setValue(View.GONE);
            viewModel.minigameFragmentVisibility.setValue(View.VISIBLE);
        });
    }

    public Hero generateHero(){

        return new Hero(mainFragmentBinding.hpBar,
                new ProgressBar[]{
                        mainFragmentBinding.hungryBar,
                        mainFragmentBinding.happyBar,
                        mainFragmentBinding.intelligenceDevelopmentBar,
                        mainFragmentBinding.hairinessBar,
                        mainFragmentBinding.tirednessBar,
                        mainFragmentBinding.moneyBar,
                        mainFragmentBinding.stressBar,
                        mainFragmentBinding.immunityBar,
                        mainFragmentBinding.nerdinessBar
                });
    }

}
