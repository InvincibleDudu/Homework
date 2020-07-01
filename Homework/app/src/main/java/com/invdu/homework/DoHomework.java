package com.invdu.homework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class DoHomework extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_homework);
        Objects.requireNonNull(getSupportActionBar()).setTitle("做作业");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = this.getIntent();
        final Bundle bundle = intent.getExtras();
        assert bundle != null;
        TextView tv = findViewById(R.id.doHomeworkTitleTextView);
        TextView tv2 = findViewById(R.id.homeworkContentTextView);
        tv.setText(bundle.getString("title"));
        tv2.setText(bundle.getString("content"));
        final int homeworkID = bundle.getInt("homeworkID");
        Button button = findViewById(R.id.buttonSubmitSub);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed = findViewById(R.id.editTextTextMultiLine);
                if (ed.getText().toString().equals("")) {
                    Toast.makeText(DoHomework.this, "答案不能为空！", Toast.LENGTH_SHORT).show();
                } else {
//                    ed.getText().toString()
//                    make an update function, use it here to update the "answer" column of the homework table.
//                    Toast.makeText(DoHomework.this, "答案：" + ed.getText().toString()+"作业ID：" + homeworkID, Toast.LENGTH_LONG).show();
                    DBHelper db = new DBHelper(DoHomework.this);
                    db.updateHomework(homeworkID, ed.getText().toString());
                    Intent i = new Intent(DoHomework.this, SubComplete.class);
                    i.putExtra("title", bundle.getString("title"));
                    i.putExtra("id", homeworkID);
                    startActivity(i);  //进行跳转
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}