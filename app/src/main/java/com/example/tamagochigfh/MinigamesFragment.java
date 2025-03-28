package com.example.tamagochigfh;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tamagochigfh.databinding.MinigamesFragmentBinding;

public class MinigamesFragment extends Fragment {
    private MinigamesFragmentBinding minigames_binding;
    private MainActivityViewModel  viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Загрузите макет фрагмента
        minigames_binding = MinigamesFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        hungryGameBtn();
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
        });
    }
    public void setVisibility(int visibility){
        if (getView()!= null) {
            getView().setVisibility(visibility);
        }
    }


}
