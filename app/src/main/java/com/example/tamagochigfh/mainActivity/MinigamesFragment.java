package com.example.tamagochigfh.mainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tamagochigfh.databinding.MinigamesFragmentBinding;
import com.example.tamagochigfh.food.HungryGameActivity;
import com.example.tamagochigfh.imunity.ImunityActivityGame;

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
        closeBtn();
        return minigames_binding.getRoot();
    }
    public void closeBtn(){
        minigames_binding.closeButton.setOnClickListener(v -> {
            viewModel.mainFragmentVisibility.setValue(View.VISIBLE);
            viewModel.minigameFragmentVisibility.setValue(View.GONE);
        });
    }
    public void hungryGameBtn(){

        minigames_binding.hungryGameButton.setOnClickListener(v -> {
            viewModel.nextActivity.setValue(HungryGameActivity.class);
        });
    }
    public void imunityGameBtn(){
        minigames_binding.imunityGameButton.setOnClickListener(v->{
            viewModel.nextActivity.setValue(ImunityActivityGame.class);
        });
    }
    public void setVisibility(int visibility){
        if (getView()!= null) {
            getView().setVisibility(visibility);
        }
    }


}
