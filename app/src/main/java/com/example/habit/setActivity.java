package com.example.habit;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class setActivity extends AppCompatActivity {

    Habit habit;

    private EditText habitText, editView;
    private HabitDao habitDao;
    private Button bgray, bblue, bred, byellow, bgreen, bblack, bseablue, bpink, borange, bdefault, bzao, bzhong, bwan, bshui, bsubmit;
    private TextView tColor;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        habit = new Habit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        habitDao = new HabitDao(setActivity.this);
        //EditText
        habitText = (EditText) findViewById(R.id.habitText);
        editView = (EditText) findViewById(R.id.editView);
        //Button
        bgray = (Button) findViewById(R.id.bgray);
        bblue = (Button) findViewById(R.id.bblue);
        bred = (Button) findViewById(R.id.bred);
        byellow = (Button) findViewById(R.id.byellow);
        bgreen = (Button) findViewById(R.id.bgreen);

        bblack = (Button) findViewById(R.id.bblack);
        bseablue = (Button) findViewById(R.id.bseablue);
        bpink = (Button) findViewById(R.id.bpink);
        borange = (Button) findViewById(R.id.borange);
        bdefault = (Button) findViewById(R.id.bdefault);

        bzao = (Button) findViewById(R.id.bzao);
        bzhong = (Button) findViewById(R.id.bzhong);
        bwan = (Button) findViewById(R.id.bwan);
        bshui = (Button) findViewById(R.id.bshui);

        //提交
        bsubmit = (Button) findViewById(R.id.bsubmit);

        //TextView
        tColor = (TextView) findViewById(R.id.tColor);

        bgray.getBackground().setColorFilter(Color.parseColor("gray"), PorterDuff.Mode.DARKEN);
        bblue.getBackground().setColorFilter(Color.parseColor("blue"), PorterDuff.Mode.DARKEN);
        bred.getBackground().setColorFilter(Color.parseColor("red"), PorterDuff.Mode.DARKEN);
        byellow.getBackground().setColorFilter(Color.parseColor("yellow"), PorterDuff.Mode.DARKEN);
        bgreen.getBackground().setColorFilter(Color.parseColor("green"), PorterDuff.Mode.DARKEN);

        bblack.getBackground().setColorFilter(Color.parseColor("black"), PorterDuff.Mode.DARKEN);
        bseablue.getBackground().setColorFilter(Color.parseColor("#32CD99"), PorterDuff.Mode.DARKEN);
        bpink.getBackground().setColorFilter(Color.parseColor("#fce5cd"), PorterDuff.Mode.DARKEN);
        borange.getBackground().setColorFilter(Color.parseColor("#ff9900"), PorterDuff.Mode.DARKEN);

        tColor.getBackground().setColorFilter(Color.parseColor("black"), PorterDuff.Mode.DARKEN);

        bgray.setOnClickListener(new MyListener());
        bblue.setOnClickListener(new MyListener());
        bred.setOnClickListener(new MyListener());
        byellow.setOnClickListener(new MyListener());
        bgreen.setOnClickListener(new MyListener());

        bblack.setOnClickListener(new MyListener());
        bseablue.setOnClickListener(new MyListener());
        bpink.setOnClickListener(new MyListener());
        borange.setOnClickListener(new MyListener());
        bdefault.setOnClickListener(new MyListener());

        bzao.setOnClickListener(new MyListener());
        bzhong.setOnClickListener(new MyListener());
        bwan.setOnClickListener(new MyListener());
        bshui.setOnClickListener(new MyListener());

        bsubmit.setOnClickListener(new MyListener());

    }

    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bgray:
                    tColor.getBackground().setColorFilter(Color.parseColor("gray"), PorterDuff.Mode.DARKEN);
                    habit.setH_color("gray");
                    Log.i("Test", "灰");
                    break;
                case R.id.bblue:
                    tColor.getBackground().setColorFilter(Color.parseColor("blue"), PorterDuff.Mode.DARKEN);
                    habit.setH_color("blue");
                    Log.i("Test", "蓝");
                    break;
                case R.id.bred:
                    tColor.getBackground().setColorFilter(Color.parseColor("red"), PorterDuff.Mode.DARKEN);
                    habit.setH_color("red");
                    Log.i("Test", "红");
                    break;
                case R.id.byellow:
                    tColor.getBackground().setColorFilter(Color.parseColor("yellow"), PorterDuff.Mode.DARKEN);
                    habit.setH_color("yellow");
                    Log.i("Test", "黄");
                    break;
                case R.id.bgreen:
                    tColor.getBackground().setColorFilter(Color.parseColor("green"), PorterDuff.Mode.DARKEN);
                    habit.setH_color("green");
                    Log.i("Test", "绿");
                    break;

                case R.id.bblack:
                    tColor.getBackground().setColorFilter(Color.parseColor("black"), PorterDuff.Mode.DARKEN);
                    habit.setH_color("black");
                    Log.i("Test", "灰");
                    break;
                case R.id.bseablue:
                    tColor.getBackground().setColorFilter(Color.parseColor("#32CD99"), PorterDuff.Mode.DARKEN);
                    habit.setH_color("#32CD99");
                    Log.i("Test", "蓝");
                    break;
                case R.id.bpink:
                    tColor.getBackground().setColorFilter(Color.parseColor("#fce5cd"), PorterDuff.Mode.DARKEN);
                    habit.setH_color("#fce5cd");
                    Log.i("Test", "红");
                    break;
                case R.id.borange:
                    tColor.getBackground().setColorFilter(Color.parseColor("#ff9900"), PorterDuff.Mode.DARKEN);
                    habit.setH_color("#ff9900");
                    Log.i("Test", "黄");
                    break;
                case R.id.bdefault:
                    tColor.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    habit.setH_color("#eeeeee");
                    Log.i("Test", "绿");
                    break;

                case R.id.bzao:
                    bzao.getBackground().setColorFilter(Color.parseColor("#ff9900"), PorterDuff.Mode.DARKEN);
                    bzhong.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    bwan.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    bshui.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    habit.setH_time(0);
                    Log.i("Test", "绿");
                    break;
                case R.id.bzhong:
                    bzao.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    bzhong.getBackground().setColorFilter(Color.parseColor("#ffd966"), PorterDuff.Mode.DARKEN);
                    bwan.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    bshui.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    habit.setH_time(0);
                    Log.i("Test", "绿");
                    break;
                case R.id.bwan:
                    bzao.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    bzhong.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    bwan.getBackground().setColorFilter(Color.parseColor("#f1c232"), PorterDuff.Mode.DARKEN);
                    bshui.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    habit.setH_time(0);
                    Log.i("Test", "绿");
                    break;
                case R.id.bshui:
                    bzao.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    bzhong.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    bwan.getBackground().setColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DARKEN);
                    bshui.getBackground().setColorFilter(Color.parseColor("#666666"), PorterDuff.Mode.DARKEN);
                    habit.setH_time(0);
                    Log.i("Test", "绿");
                    break;

                case R.id.bsubmit:
                    habit.setH_thing(habitText.getText().toString());
                    habit.setH_text(editView.getText().toString());
                    //数据库添加

                    if (!habitText.getText().toString().equals("")){
                        habit.setH_Id(habitDao.getMaxId());
                        habitDao.insertDate(habit);

                        Toast.makeText(getApplicationContext(), "提交成功",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "提交失败",
                                Toast.LENGTH_SHORT).show();
                    }
                    Log.i("Test", habit.toString());
                    onBackPressed();
                    break;
            }
        }
    }
}
