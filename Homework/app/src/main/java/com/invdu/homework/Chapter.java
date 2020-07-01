package com.invdu.homework;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.invdu.homework.bean.Homework;

import java.util.List;
import java.util.Objects;

public class Chapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        Objects.requireNonNull(getSupportActionBar()).setTitle("查看对应章节");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView t = findViewById(R.id.chapter_textView);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        int id = bundle.getInt("id");
        String chapter = "chapter";
//        System.out.println("/n ID got in Chapter Act: " + id);
        DBHelper db = new DBHelper(Chapter.this);
        List<Homework> allHomework = db.getAllHomework();
        for (int i = 0; i < allHomework.size(); i++) {
            Homework h = allHomework.get(i);
            if (h.getId() == id)
                chapter = h.getChapter();
        }
        t.setText(chapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}