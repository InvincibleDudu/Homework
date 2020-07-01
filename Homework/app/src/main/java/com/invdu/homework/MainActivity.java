package com.invdu.homework;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.invdu.homework.bean.Homework;
import com.invdu.homework.bean.SubQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

@SuppressLint({"ViewHolder", "SetTextI18n"})
public class MainActivity extends AppCompatActivity {

    ListView listView;
    ListView listViewCompleted;
    //    String[] mTitle = {"ListView", "RecView", "GridView"};
//    String[] mTitleCompleted = {"ListView", "RecView", "GridView"};
    //    String[] mDescription = {"ListView is the legacy.", "RecView is the modern version of the ListView. " +
//            "But it's just a pain in the butt to set it up properly as a beginner.", "GridView is ooold.", "", "", "", ""};
//    String[] mContent = {"光看这些文字会觉得ListView是个加强版的Spinner，不但可以直接在页面上展示列表，而且能设置分隔线与点击监听器。" +
//            "事实上，ListView很令人头痛，使用过程中经常出现意想不到的状况，比如分隔线就容 易出状况，下面演示分隔线的测试代码片段：\n",
//            "如果说TabLayout是导航栏一节的压轴兵器，那么循环视图RecyclerView就是本章的终极兵器，因为功能实在是太强大了，强大到秒杀列表视图ListView，" +
//                    "再秒杀网格视图GridView，还能秒杀瀑布流网格开源框架StaggeredGridView和PinterestLikeAdapterView，总之学会了RecyclerView，" +
//                    "你的App武功必然提高一个层次。", "可以看到，网格视图不像列表视图那样有指定分隔线的方法，但这并不意味着GridView就没法设置分隔线。" +
//            "通过变通的方式也能给GridView设置分隔线。具体地说，就是先给GridView设置背景色（例如黑色），以及网格之间的水平间距和垂直间距；" +
//            "然后给网格项设置背景色（例如白色），这样只有网格间距是黑色，从而间接设置了黑色的分隔线"};
//    private SqlScoutServer sqlScoutServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(MainActivity.this);  //创建数据库实例对象
        //    int[] mScore = {95, 43, 82};
        //开启Service
        SQLiteStudioService.instance().start(this);
        //关闭Service
//        SQLiteStudioService.instance().stop(this);
        Objects.requireNonNull(getSupportActionBar()).setTitle("作业");   //设置标题栏标题
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Homework homework = new Homework("1.1", "TextView的使用方法", "TextView是最基础的文本显示控件。", -1, true, 1);
//        db.addHomework(homework);
//        homework = new Homework("1.2", "ImageView的使用方法", "ImageView是图像显示控件，与图形显示有关。", -1, true, 2);
//        db.addHomework(homework);
//        homework = new Homework("1.3", "ScrollView的使用方法", "本节带来的是Android基本UI控件中的第十个：ScrollView(滚动条)，或者我们应该叫他 竖直滚动条，对应的另外一个水平方向上的滚动条：HorizontalScrollView。", -1, true, 3);
//        db.addHomework(homework);
//        homework = new Homework("2.1", "GridView的使用方法", "除了列表视图，网格视图GridView也是常见的适配器视图，用于分行分列显示表格信息，比ListView更 适合展示商品清单。", -1, true, 4);
//        db.addHomework(homework);
//        homework = new Homework("2.2", "ListView的使用方法", "光看这些文字会觉得ListView是个加强版的Spinner，不但可以直接在页面上展示列表，而且能设置分隔线与点击监听器。", "ListView需要先设置一个Adapter，然后才能正常使用。", 85, true, 5);
//        db.addHomework(homework);
//        homework = new Homework("3.1", "RecyclerView的使用方法", "循环视图RecyclerView就是本章的终极兵器。", -1, true, 6);
//        db.addHomework(homework);
//        homework = new Homework("4.1", "碎片适配器的使用方法", "Fragment是个特别的存在，有点像报纸上的专栏，看起来只占据页面的一小块，但是这一小块有自己 的生命周期，可以自行其事，仿佛独立王国。", -1, false, 7);
//        db.addHomework(homework);

        List<Homework> homeworkList = db.getAllHomework();
        List<SubQuestion> allSubQuestions = db.getAllSubQuestions();
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> descriptionList = new ArrayList<>();
        ArrayList<String> cTitleList = new ArrayList<>();
        ArrayList<Integer> scoreList = new ArrayList<>();
        ArrayList<Integer> homeworkIdList = new ArrayList<>();
        ArrayList<Integer> cHomeworkIdList = new ArrayList<>();     //Completed Homework ID List
        ArrayList<String> contentList = new ArrayList<>();

        for (int i = 0; i < homeworkList.size(); i++) {
            Homework h = homeworkList.get(i);
            if (h.getScore() == -1 && (h.getAnswer() == null)) {    //If score = -1 and answer is null, then this would be a incomplete homework
//                Toast.makeText(this, h.toString(), Toast.LENGTH_SHORT).show();
                homeworkIdList.add(h.getId());
                titleList.add(h.getTitle());
                descriptionList.add(h.getDescription());
                int id = h.getIncludedQuestions();
                for (int j = 0; j < allSubQuestions.size(); j++) {
                    SubQuestion sub = allSubQuestions.get(j);
                    if (sub.getId() == id)
                        contentList.add(sub.getContent());
                }
            } else {    //This homework has been completed, regardless of the status of reviewing.
                cTitleList.add(h.getTitle());
                scoreList.add(h.getScore());
                cHomeworkIdList.add(h.getId());
            }
        }

        final String[] mTitle = titleList.toArray(new String[0]);
        String[] mDescription = descriptionList.toArray(new String[0]);
        String[] mTitleCompleted = cTitleList.toArray(new String[0]);
        final String[] mContent = contentList.toArray(new String[0]);

        int[] mScore = new int[scoreList.size()];
        for (int i = 0; i < mScore.length; i++)
            mScore[i] = scoreList.get(i);

        final int[] cHomeworkID = new int[cHomeworkIdList.size()];
        for (int i = 0; i < cHomeworkID.length; i++)
            cHomeworkID[i] = cHomeworkIdList.get(i);

        final int[] mHomeworkID = new int[homeworkIdList.size()];
        for (int i = 0; i < mHomeworkID.length; i++)
            mHomeworkID[i] = homeworkIdList.get(i);

        listView = findViewById(R.id.listView1);
        listViewCompleted = findViewById(R.id.listView2);

        Adapter adapter = new Adapter(this, mTitle, mDescription, mHomeworkID);     //Populate the listview
        listView.setAdapter(adapter);

        AdapterCompleted adapterCompleted = new AdapterCompleted(this, mTitleCompleted, mScore, cHomeworkID);
        listViewCompleted.setAdapter(adapterCompleted);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    openDoHomework(mTitle[position], mContent[position], mHomeworkID[position]);//Open DoHomework Activity when pressing the listView item
                } catch (Exception ignored) {
                }
            }
        });

    }

    private void openDoHomework(String title, String content, int homeworkID) {
        Intent intent = new Intent(this, DoHomework.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("homeworkID", homeworkID);
        startActivity(intent);
    }


    class Adapter extends ArrayAdapter<String> {       //Custom ListView Adapter
        Context context;
        String[] rtitle;
        String[] rDescription;
        int[] mHomeworkID;

        Adapter(Context c, String[] title, String[] description, int[] id) {
            super(c, R.layout.list_item, title);
            context = c;
            this.rtitle = title;
            this.rDescription = description;
            this.mHomeworkID = id;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            TextView t = v.findViewById(R.id.homeworkTitle);
            TextView t2 = v.findViewById(R.id.homeworkDescription);
            Button button = v.findViewById(R.id.chapter_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {    //Open Chapter Activity when pressing the button in the first ListView
                    int id = mHomeworkID[position]; //Get the id according to the position of the ListView
                    Intent intent = new Intent(MainActivity.this, Chapter.class);
                    intent.putExtra("id", id);  //Pass the value of the id to the Chapter Activity
                    startActivity(intent);
                }
            });
            t.setText(rtitle[position]);
            t2.setText(rDescription[position]);
            return v;
        }
    }

    class AdapterCompleted extends ArrayAdapter<String> {
        Context context;
        String[] rtitle;
        int[] rScore;
        int[] mHomeworkID;

        AdapterCompleted(Context c, String[] title, int[] score, int[] id) {
            super(c, R.layout.list_item_completed, title);
            context = c;
            this.rtitle = title;
            this.rScore = score;
            this.mHomeworkID = id;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = getLayoutInflater().inflate(R.layout.list_item_completed, parent, false);
            TextView t = v.findViewById(R.id.homeworkTitle);
            TextView t2 = v.findViewById(R.id.homeworkScore);
            t.setText(rtitle[position]);
            if (rScore[position] < 60)
                t2.setTextColor(Color.RED);
            t2.setText(rScore[position] + "分");
            if (rScore[position] < 0) {
//              @Deprecated  t2.setTextColor(MainActivity.this.getResources().getColor(R.color.colorPrimary));
                t2.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                t2.setText("待批阅");
            }
            Button button = v.findViewById(R.id.enter_discussion);
            Button buttonDetails = v.findViewById(R.id.see_details);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    openDiscussion(mHomeworkID[position]);
//                    Toast.makeText(MainActivity.this, "position: " + position, Toast.LENGTH_SHORT).show();
                    int id = mHomeworkID[position];
                    Intent intent = new Intent(MainActivity.this, DiscussionActivity.class);
//                    Toast.makeText(MainActivity.this, "ID in openDiscussion: " + id, Toast.LENGTH_SHORT).show();
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            });
            buttonDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, SubKey.class);
                    int id = mHomeworkID[position];
//                    Toast.makeText(MainActivity.this, "ID in openDiscussion: " + id, Toast.LENGTH_SHORT).show();
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            });
            return v;
        }
    }
}