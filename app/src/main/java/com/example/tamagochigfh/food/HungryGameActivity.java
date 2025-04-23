package com.example.tamagochigfh.food;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tamagochigfh.R;

import com.example.tamagochigfh.databinding.HungryGameActivityBinding;
import com.example.tamagochigfh.mainActivity.MainActivity;
import com.example.tamagochigfh.mainActivity.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class HungryGameActivity extends AppCompatActivity {

    private HungryGameActivityBinding binding;
    private HungryGameViewModel viewModel;
    private ArrayList<ImageView> images = new ArrayList<>();
    private Handler handler =  new Handler(Looper.getMainLooper());
    private static final String ID_KEY = "ID";
    private static final String VALUE_KEY = "VALUE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HungryGameActivityBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(HungryGameViewModel.class);
        viewModel.createLevl();
        viewModel.timerThread();
        viewModel.spawnThread();
        setContentView(binding.getRoot());
        viewModel.isActivity_life().observe(this, life->{
                if (!life){
                    binding.finalScore.bringToFront();
                    binding.backBtn.bringToFront();
                    binding.backBtn.setVisibility(ViewGroup.VISIBLE);
                    binding.backBtn.setOnClickListener(v->{
                        Intent intent = new Intent(HungryGameActivity.this, MainActivity.class);
                        // Запускаем новую активность
                        intent.putExtra(ID_KEY, 1);
                        intent.putExtra(VALUE_KEY, viewModel.getHungry_points());
                       // Hero.getInnstance();
                        startActivity(intent);
                    });
                    binding.finalScore.setVisibility(ViewGroup.VISIBLE);
                    binding.finalScore.setText(getString( R.string.your_score,
                            Integer.valueOf(viewModel.getHungry_points()).toString()));

                }
        });
        viewModel.getTime().observe(this,time->{
            binding.timerText.setText(getString( R.string.timer_string,
                    Objects.requireNonNull(viewModel.getTime().getValue()).toString()));
        });
        viewModel.getLastFood().observe(this, last->{
                newFood(last);
        });
        viewModel.getAnimation_tick().observe(this, tick->{
            animationTick();
        });

    }

    private void newFood(Food food){

            ImageView  newImage = new ImageView(this);
            newImage.setImageResource(food.getImage());
            binding.container.addView(newImage);
            newImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        newImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        newImage.setX(viewModel.getNewPosition(binding.container.getWidth(),
                                newImage.getWidth()));

                        newImage.setOnClickListener(v -> {
                            if(Boolean.TRUE.equals(viewModel.isActivity_life().getValue())){
                                if (food.isTasty()){
                                    viewModel.addHungry_points();
                                }
                                else{
                                    viewModel.subtractHungry_points();
                                }
                                ((ViewGroup) v.getParent()).removeView(v); // Удалить ImageView
                            }

                        });
                        food.setX(newImage.getX());
                    }
            });

            images.add(newImage);
    }
    private void animationTick(){

        for(ImageView image: images){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    image.setY(image.getY()+viewModel.getSpeed());
                }
            });
        }

    }
}
