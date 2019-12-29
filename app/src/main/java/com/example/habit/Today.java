package com.example.habit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class Today extends Fragment {

    private String context;//用于设置textview的值
    private TextView mTextView;//用于获取textview控件
    private HabitDao habitDao;
    private static View view;
    ArrayList<Habit> results = new ArrayList<Habit>();
    private static ListView list;
    MyAdapter adapter;

    private List<Habit> habitList;
    private int selectId = -1;
    private int posId = -1;


    public Today(String context){
        this.context = context;
    }//构造函数

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_today,container,false);
        list = (ListView)view.findViewById(R.id.list_data);
        mTextView = (TextView)view.findViewById(R.id.txt_content);
        mTextView.setText(context);

        habitDao = new HabitDao(view.getContext());
        habitList = habitDao.getAllDate();

        if (! habitDao.isDataExist()){
            habitDao.initTable();
        }

        if (habitList != null){
            adapter = new MyAdapter(view.getContext());
            list.setAdapter(adapter);
           // showDateListView.setAdapter(adapter);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //parent 代表listView View 代表 被点击的列表项 position 代表第几个 id 代表列表编号
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.i("getMsg",""+habitList.get(position).getH_OK());
                posId = position;
                if(habitList.get(position).getH_OK()==1){
                    Toast.makeText(view.getContext(), "今天您已签到啦！", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(view.getContext(), habitList.get(posId).getH_text(), Toast.LENGTH_SHORT).show();
                    selectId = habitList.get(position).getH_Id();
                    habitDao.updateHOk(habitList.get(position).getH_Id(),habitList.get(position).getH_time());
                    refreshHabitList();
                }
            }
        });

        return view;
    }

    private void refreshHabitList(){
        // 注意：千万不要直接赋值，如：habitList = habitDao.getAllDate() 此时相当于重新分配了一个内存 原先的内存没改变 所以界面不会有变化
        // Java中的类是地址传递 基本数据才是值传递
        habitList.clear();
        habitList.addAll(habitDao.getAllDate());
        adapter.notifyDataSetChanged();
    }

    public  class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        public MyAdapter(Context c){
            inflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount() {
            return habitList.size();
        }

        @Override
        public Object getItem(int i) {
            return habitList.get(i).getH_Id();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.layout_adapter,null);
            TextView thing1,day1,text1;
            TextView thing2,day2,text2;
            TextView thing3,day3,text3;

            thing1 = (TextView) view.findViewById(R.id.thing1);
            day1 = (TextView) view.findViewById(R.id.day1);
            text1 = (TextView) view.findViewById(R.id.text1);

            Log.i("msg","获取："+habitList.get(i).toString());


            thing1.setText(habitList.get(i).getH_thing());
            if(habitList.get(i).getH_color()!="")Log.i("msg",habitList.get(i).getH_color());
            else {
                //Log.i("msg",textColor[i]);
                habitList.get(i).setH_color("#afafaf");
            }
            day1.getBackground().setColorFilter(Color.parseColor(habitList.get(i).getH_color()), PorterDuff.Mode.DARKEN);
            if (habitList.get(i).getH_time()>=0)text1.setText(habitList.get(i).getH_time()+" 天");
            else text1.setText(habitList.get(i).getH_time());

            return view;
        }
    }
}