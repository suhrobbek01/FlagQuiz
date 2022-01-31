package com.example.web_viewtask2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.web_viewtask2.databinding.ActivityDialogBinding;

public class DialogActivity extends AppCompatActivity {

    private ActivityDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(DialogActivity.this, R.color.white));

        binding.tv.setText("Siz bu bosqichni tugatdingiz");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DialogActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}