package com.example.tamagochigfh.mainActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tamagochigfh.databinding.MinigamesFragmentBinding;
import com.example.tamagochigfh.develop.DevelopGameActivity;
import com.example.tamagochigfh.food.HungryGameActivity;
import com.example.tamagochigfh.happy.HappyActivityGame;
import com.example.tamagochigfh.imunity.ImunityActivityGame;
import com.example.tamagochigfh.money.MoneyGameActivity;
import com.example.tamagochigfh.stress.StressGameActivity;
import com.example.tamagochigfh.tiredness.TirednessGameActivity;

public class MinigamesFragment extends Fragment {
    private MinigamesFragmentBinding minigames_binding;
    private MainActivityViewModel  viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Загрузите макет фрагмента
        minigames_binding = MinigamesFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        hungryGameBtn();
        imunityGameBtn();
        happyGameBtn();
        tirednessGameBtn();
        developGameBtn();
        stressGameBtn();
        moneyGameBtn();
        closeBtn();
        return minigames_binding.getRoot();
    }

    private void happyGameBtn() {
        minigames_binding.happyGameButton.setOnClickListener(v -> {
            viewModel.nextActivity.setValue(HappyActivityGame.class);
        });
    }

    public void closeBtn(){
        minigames_binding.closeButton.setOnClickListener(v -> {
            viewModel.mainFragmentVisibility.setValue(View.VISIBLE);
            viewModel.minigameFragmentVisibility.setValue(View.GONE);
        });
    }
    public void tirednessGameBtn(){

        minigames_binding.tirednessGameButton.setOnClickListener(v -> {
            viewModel.nextActivity.setValue(TirednessGameActivity.class);
        });
    }
    public void hungryGameBtn(){

        minigames_binding.hungryGameButton.setOnClickListener(v -> {
            viewModel.nextActivity.setValue(HungryGameActivity.class);
        });
    }

    public void developGameBtn(){

        minigames_binding.developGameButton.setOnClickListener(v -> {
            viewModel.nextActivity.setValue(DevelopGameActivity.class);
        });
    }
    public void stressGameBtn(){
        minigames_binding.stressGameButton.setOnClickListener(v -> {
            viewModel.nextActivity.setValue(StressGameActivity.class);
        });
    }
    public void imunityGameBtn(){
        minigames_binding.imunityGameButton.setOnClickListener(v->{
            viewModel.nextActivity.setValue(ImunityActivityGame.class);
        });
    }
    public void moneyGameBtn(){
        minigames_binding.moneyGameButton.setOnClickListener(v->{
            viewModel.nextActivity.setValue(MoneyGameActivity.class);
        });
    }
    public void setVisibility(int visibility){
        if (getView()!= null) {
            getView().setVisibility(visibility);
        }
    }


}
