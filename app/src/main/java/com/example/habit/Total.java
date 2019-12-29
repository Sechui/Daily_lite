package com.example.habit;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class Total extends Fragment {

    private List<Habit> habitList;
    private HabitDao habitDao;

    private String context;//用于设置textview的值
    private TextView mTextView;//用于获取textview控件
    private Button addHabbit;
    View cFragment;

    private List<Habit> lists;
    ListView list;
    private int iETContentHeight = 0;	// Text控件高度
    int num;
    private float fDimRatio = 1.0f; // 尺寸比例（实际尺寸/xml文件里尺寸）
    private LinearLayout llContentView; // 外围的LinearLayout容器
    private int selectId = -1;
    private int posId = -1;

    MyAdapter adapter;

    public Total(String context){
        this.context = context;
    }//构造函数

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cFragment = inflater.inflate(R.layout.activity_total,container,false);
        list = (ListView)cFragment.findViewById(R.id.list_data);
        mTextView = (TextView)cFragment.findViewById(R.id.txt_content);
        addHabbit = (Button)cFragment.findViewById(R.id.addHabbit);
        mTextView.setText(context);
        num = 0;

        habitDao = new HabitDao(cFragment.getContext());
        habitList = habitDao.getAllDate();

        llContentView = (LinearLayout) cFragment.findViewById(R.id.content_views);
        if (habitList != null){
            adapter = new MyAdapter(cFragment.getContext());

            list.setAdapter(adapter);

        }
        setListener(cFragment);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posId = position;
                if (habitList.get(position).getH_Id() < 0) {
                    Toast.makeText(view.getContext(), "删除失败！", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    selectId = habitList.get(position).getH_Id();
                    habitDao.deleteHabit(habitList.get(position).getH_Id());
                    Toast.makeText(view.getContext(), "删除成功！", Toast.LENGTH_SHORT).show();
                    refreshHabitList();
                    return true;
                }
            }
        });

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

        try {
            //lists = ConnectMySQL.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cFragment;
    }

    private void refreshHabitList(){
        // 注意：千万不要直接赋值，如：habitList = habitDao.getAllDate() 此时相当于重新分配了一个内存 原先的内存没改变 所以界面不会有变化
        // Java中的类是地址传递 基本数据才是值传递
        habitList.clear();
        habitList.addAll(habitDao.getAllDate());
        adapter.notifyDataSetChanged();
    }

    private void setListener(final View cFragment) {

        // 按钮点击事件
        addHabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fDimRatio = iETContentHeight / 80;
                LinearLayout course_linearLayout  = (LinearLayout)cFragment.findViewById(R.id.content_views);


                // 调用数据库工具类DBUtils的getInfoByName方法获取数据库表中数据

                Intent intent = new Intent(getActivity(),setActivity.class);
                startActivity(intent);

                lists =new ArrayList<Habit>();

                if(lists!=null){
                    //
                   // Log.i("msg",""+list.get(1).getH_color());
                }
            }
        });

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
            view = inflater.inflate(R.layout.layout_apt,null);
            TextView thing1,text1;
            LinearLayout day1;

            thing1 = (TextView) view.findViewById(R.id.thing1);
            day1 = (LinearLayout) view.findViewById(R.id.day1);
            text1 = (TextView) view.findViewById(R.id.text1);

            Log.i("msg","获取："+i);


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