package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        // создаем экземпляр класса GameView
        GameView gameView = new GameView(this, null);
        // устанавливаем GameView в качестве контента активности
        setContentView(gameView);
    }
}