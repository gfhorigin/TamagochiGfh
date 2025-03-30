package com.example.tamagochigfh.food;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tamagochigfh.R;
import com.example.tamagochigfh.databinding.HungryGameActivityBinding;
import com.example.tamagochigfh.mainActivity.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class HungryGameActivity extends AppCompatActivity {

    private HungryGameActivityBinding binding;
    private HungryGameViewModel viewModel;
    private ArrayList<ImageView> images = new ArrayList<>();
    private Handler handler =  new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HungryGameActivityBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(HungryGameViewModel.class);
        viewModel.createLevl();
        viewModel.timerThread();
        viewModel.spawnThread();
        setContentView(binding.getRoot());

        viewModel.getTime().observe(this,time->{
            binding.timerText.setText(getString( R.string.timer_string,
                    Objects.requireNonNull(viewModel.getTime().getValue()).toString()));
        });
        viewModel.getLastFood().observe(this, last->{
                newFood(last);
        });

//        ImageView imageView = new ImageView(this);
//
//        // Установить изображение из ресурсов
//        imageView.setImageResource(R.drawable.tomato);
//
//        binding.container.addView(imageView);
//

//
//
//        imageView.setOnClickListener(v->{
//            imageView.setX(imageView.getX()+100);
//        });
    }
    private void timerThread(){
        binding.timerText.setText(getString( R.string.timer_string,
                Objects.requireNonNull(viewModel.getTime().getValue()).toString()));

    }
    private void newFood(Food food){

            ImageView  newImage = new ImageView(this);
            newImage.setImageResource(food.getImage());
            binding.container.addView(newImage);
            newImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        newImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        Log.d("TEST",newImage.getWidth()+"");
                        newImage.setX(viewModel.getNewPosition(binding.container.getWidth(),
                                newImage.getWidth()));
                        food.setX(newImage.getX());
                    }
            });


            images.add(newImage);
    }
}
