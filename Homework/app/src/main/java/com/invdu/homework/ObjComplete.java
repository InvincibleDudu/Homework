package com.invdu.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

public class ObjComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obj_complete);
        Objects.requireNonNull(getSupportActionBar()).setTitle("作业完成");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}