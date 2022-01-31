package com.example.web_viewtask2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.web_viewtask2.databinding.ActivityRulesBinding;

public class Rules extends AppCompatActivity {

    private ActivityRulesBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRulesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(Rules.this, R.color.white));


        sharedPreferences = getSharedPreferences("FlagQuiz", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String rules = sharedPreferences.getString("rules", "");
        binding.tv2.setText(rules);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}