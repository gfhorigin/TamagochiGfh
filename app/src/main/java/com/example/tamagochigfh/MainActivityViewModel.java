package com.example.tamagochigfh;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.example.tamagochigfh.databinding.ActivityMainBinding;
import com.example.tamagochigfh.databinding.MainFragmentBinding;
import com.example.tamagochigfh.databinding.MinigamesFragmentBinding;

public class MainActivityViewModel extends ViewModel {
    public Hero hero;

    public void setHero(Hero hero){
        this.hero = hero;
    }
    public Hero getHero() {
        return hero;
    }
}
