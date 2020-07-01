package com.invdu.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

public class DoHomeworkObj extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_homework_obj);

        Objects.requireNonNull(getSupportActionBar()).setTitle("做作业");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}