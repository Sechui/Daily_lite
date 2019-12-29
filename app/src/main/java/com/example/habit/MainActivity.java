package com.example.habit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    HabitDao habitDao;
    private ListView list;
    private Button tabbar_btn;

    private int selectId = -1;
    private int posId = -1;

    List<Habit> habitList = new ArrayList<Habit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        habitDao = new HabitDao(MainActivity.this);

        //Log.i("msg","获取："+ habitDao.updateTime());
        sp = getSharedPreferences("habit", MODE_PRIVATE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        if (isToday()) {
            //不是同一天的时候做一些啥的
            habitDao.updateTime();
        }

        tabbar_btn = (Button) findViewById(R.id.tabbar_btn);
        selectorMethod();
    }

    private boolean isToday() {
        editor = sp.edit();
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month;
        int date = t.monthDay;
        int minute = t.minute;
        int oldminute = sp.getInt("minute", -1);
        int oldyear = sp.getInt("year", -1);
        int oldmonth = sp.getInt("month", -1);
        int olddate = sp.getInt("date", -1);
        saveMsg();
        //the first
        if (oldminute == -1 || oldyear == -1 || oldmonth == -1 || olddate == -1) {
            return true;
        }
        if (olddate < date) {
            return true;
        } else{
            return false;
        }
    }

    private void saveMsg() {
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month;
        int date = t.monthDay;
        int minute = t.minute;
        editor.putInt("year", year);
        editor.putInt("month", month);
        editor.putInt("date", date);
        editor.putInt("minute", minute);
        editor.commit();
    }

    public void selectorMethod() {
        // 展示自定义Tabbar
        tabbar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("展示自定义Tabbar");
                // 跳转页面
                Intent intent = new Intent(MainActivity.this, ShowTabbarActivity.class);
                startActivity(intent);
            }
        });
    }

}

