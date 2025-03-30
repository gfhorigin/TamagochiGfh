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

import java.util.Objects;

public class HungryGameActivity extends AppCompatActivity {

    private HungryGameActivityBinding binding;
    private HungryGameViewModel viewModel;
    private Handler handler =  new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HungryGameActivityBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(HungryGameViewModel.class);
        viewModel.createLevl();
        viewModel.timerThread();
        setContentView(binding.getRoot());

        viewModel.getTime().observe(this,time->{
            timerThread();
        });

//        ImageView imageView = new ImageView(this);
//
//        // Установить изображение из ресурсов
//        imageView.setImageResource(R.drawable.tomato);
//
//        binding.container.addView(imageView);
//
//        imageView.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
//            @Override
//            public void onDraw() {
//                Log.d("Width", String.valueOf(binding.container.getWidth()));
//                imageView.setX((float) binding.container.getWidth()/2- (float) imageView.getWidth() /2);
//                imageView.setY((float) binding.container.getHeight()/2- (float) imageView.getHeight() /2);
//            }
//        });
//
//
//        imageView.setOnClickListener(v->{
//            imageView.setX(imageView.getX()+100);
//        });
    }
    private void timerThread(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("Vis TIME", "VIS");
                binding.timerText.setText(getString( R.string.timer_string,
                        Objects.requireNonNull(viewModel.getTime().getValue()).toString()));
            }
        });
    }
}
