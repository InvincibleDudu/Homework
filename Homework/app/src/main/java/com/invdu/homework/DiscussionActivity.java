package com.invdu.homework;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.invdu.homework.bean.Discussion;
import com.invdu.homework.bean.Homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiscussionActivity extends AppCompatActivity {
    //    String[] mName = {"小白", "小绿", "老师", "我"};
//    String[] mTime = {"2020-06-16 16:41:19", "2020-06-16 16:42:19", "2020-06-16 16:43:19", "2020-06-16 16:45:19"};
//    String[] mMessage = {"SharedPreference好难啊", "如何通过作业巩固SharedPreference的使用方法？", "这也叫难？建议多康康视频", "SharedPreference学会了，如何学习SQLite呢？"};
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        Objects.requireNonNull(getSupportActionBar()).setTitle("作业讨论");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv = findViewById(R.id.discussionTitleTextView);

        final ListView listView = findViewById(R.id.listViewDiscussion);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final int id = bundle.getInt("id");     //Get the id value from other activities
        String title = "title";
        List<Homework> allHomework = db.getAllHomework();   //Get the homework title from DB according to the ID
        for (int i = 0; i < allHomework.size(); i++) {
            Homework h = allHomework.get(i);
            if (h.getId() == id)
                title = h.getTitle();
        }
        tv.setText(title);

//        String id = bundle.getString("id");

        List<Discussion> dList = db.getAllDiscussions();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> timeList = new ArrayList<>();
        ArrayList<String> messageList = new ArrayList<>();
        ArrayList<Integer> dIdList = new ArrayList<>();


        for (int i = 0; i < dList.size(); i++) {
            Discussion d = dList.get(i);
            if (d.getId() == id) {
                nameList.add(d.getUsername());
                timeList.add(d.getTime());
                messageList.add(d.getMessage());
            }
        }
        String[] mName = nameList.toArray(new String[0]);
        String[] mTime = timeList.toArray(new String[0]);
        String[] mMessage = messageList.toArray(new String[0]);

//        ArrayList<Discussion> list = new ArrayList<>();
//        Discussion d = new Discussion(1, "消息1", "2020-06-16 16:41:19", "杜佳晨");
//        list.add(d);
//        d = new Discussion(1, "消息2", "2020-06-16 16:42:19", "杜佳晨2");
//        list.add(d);
//        d = new Discussion(1, "消息3", "2020-06-16 16:43:19", "老师");
//        list.add(d);
//
//        ArrayAdapter<Discussion> arrayAdapter = new ArrayAdapter<Discussion>
//                (DiscussionActivity.this, android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(arrayAdapter);

        Adapter adapter = new Adapter(DiscussionActivity.this, mName, mTime, mMessage);
        listView.setAdapter(adapter);


        Button button = findViewById(R.id.discussion_send_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    //Update ListView from DB again after pressing the post button
                EditText ed = findViewById(R.id.editTextDiscussion);
                Discussion d = new Discussion(id, ed.getText().toString(), DateUtil.getNowDateTimeFormat(), "杜佳晨");
                db.addDiscussion(d);

                List<Discussion> dList = db.getAllDiscussions();
                ArrayList<String> nameList = new ArrayList<>();
                ArrayList<String> timeList = new ArrayList<>();
                ArrayList<String> messageList = new ArrayList<>();
                ArrayList<Integer> dIdList = new ArrayList<>();

                for (int i = 0; i < dList.size(); i++) {
                    d = dList.get(i);
                    if (d.getId() == id) {
                        nameList.add(d.getUsername());
                        timeList.add(d.getTime());
                        messageList.add(d.getMessage());
                    }
                }
                String[] mName = nameList.toArray(new String[0]);
                String[] mTime = timeList.toArray(new String[0]);
                String[] mMessage = messageList.toArray(new String[0]);
                Adapter adapter = new Adapter(DiscussionActivity.this, mName, mTime, mMessage);
                listView.setAdapter(adapter);
            }
        });

    }

    class Adapter extends ArrayAdapter<String> {
        Context context;
        String[] rName;
        String[] rTime;
        String[] rMessage;
//        int[] rimgs;

        Adapter(Context c, String[] name, String[] time, String[] message) {
            super(c, R.layout.list_item_discussion, name);
            context = c;
            this.rName = name;
            this.rTime = time;
            this.rMessage = message;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = getLayoutInflater().inflate(R.layout.list_item_discussion, parent, false);
            TextView t = v.findViewById(R.id.username);
            TextView t2 = v.findViewById(R.id.textViewTime);
            TextView t3 = v.findViewById(R.id.messageTextView);
            t.setText(rName[position]);
            t2.setText(rTime[position]);
            t3.setText(rMessage[position]);
            return v;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {      //Use this so that the go back animation matches the logic
        finish();
        return true;
    }
}