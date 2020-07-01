package com.invdu.homework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.invdu.homework.bean.Homework;
import com.invdu.homework.bean.SubQuestion;

import java.util.List;
import java.util.Objects;

public class SubKey extends AppCompatActivity {
    DBHelper db = new DBHelper(this);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_key);

        Objects.requireNonNull(getSupportActionBar()).setTitle("查看已完成作业");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tv = findViewById(R.id.sub_key_title);
        TextView tv2 = findViewById(R.id.sub_key_answer_content);
        TextView tvDscpt = findViewById(R.id.sub_key_content);
        TextView tv3 = findViewById(R.id.sub_key_comment_content);
        TextView tvScore = findViewById(R.id.sub_key_score);
        Button button = findViewById(R.id.enter_discussion);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final int id = bundle.getInt("id");

        System.out.println("\nID in SubKeyAct: " + id);
        String title = "title";
        String answer = "answer";
        String comment = "comment";
        String content = "Description";
        int score = 75;
        int incQuest = 0;
        List<Homework> allHomework = db.getAllHomework();
        for (int i = 0; i < allHomework.size(); i++) {
            Homework h = allHomework.get(i);
            if (h.getId() == id) {
                title = h.getTitle();
                answer = h.getAnswer();
                score = h.getScore();
                incQuest = h.getIncludedQuestions();
            }
        }
        tv.setText(title);
        tv2.setText(answer);
        List<SubQuestion> allSub = db.getAllSubQuestions();
        for (int i = 0; i < allSub.size(); i++) {
            SubQuestion sub = allSub.get(i);
            if (sub.getId() == incQuest) {
                content = sub.getContent();
                comment = sub.getComment();
            }
        }
        tvDscpt.setText(content);
        if (score == -1) {
            tvScore.setText("待批阅");
            tv3.setText("待批阅");
        } else {
            tvScore.setText(score + "分");
            tv3.setText(comment);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SubKey.this, DiscussionActivity.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}