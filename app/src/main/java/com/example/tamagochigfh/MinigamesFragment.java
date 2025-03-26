package com.example.tamagochigfh;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tamagochigfh.databinding.MinigamesFragmentBinding;

public class MinigamesFragment extends Fragment {
    private MinigamesFragmentBinding minigames_binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Загрузите макет фрагмента
        minigames_binding = MinigamesFragmentBinding.inflate(inflater, container, false);
        closeBtn();
        hungryGameBtn();

        return minigames_binding.getRoot();
    }
    public void hungryGameBtn(){

        minigames_binding.hungryGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLOSE","CLIOCK");
            }
        });
    }

    public void closeBtn(){
        minigames_binding.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.getBinding().fragmentContainer.setVisibility(View.GONE);
            }
        });
    }

}
