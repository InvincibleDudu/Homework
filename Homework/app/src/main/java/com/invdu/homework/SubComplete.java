package com.invdu.homework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.invdu.homework.bean.Discussion;

import java.util.Objects;

public class  SubComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_complete);

        Objects.requireNonNull(getSupportActionBar()).setTitle("作业完成");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button returnButton = findViewById(R.id.subCompleteReturnButton);
        Button discussionButton = findViewById(R.id.subCompleteDiscussButton);
        TextView tv = findViewById(R.id.textView);
        Intent intent = this.getIntent();
        final Bundle bundle = intent.getExtras();
        assert bundle != null;
        tv.setText(bundle.getString("title"));
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SubComplete.this, MainActivity.class);
                startActivity(i);  //进行跳转
            }
        });

        discussionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SubComplete.this, DiscussionActivity.class);
                i.putExtra("id", bundle.getInt("id"));
                startActivity(i);  //进行跳转
            }
        });

    }

}