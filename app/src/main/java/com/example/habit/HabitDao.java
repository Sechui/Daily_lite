package com.example.habit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class HabitDao {
    private static final String TAG = "HabitDao";

    // 列定义
    private final String[] Habit_COLUMNS = new String[] {"h_Id", "h_thing","h_color","h_time","h_text","h_OK"};

    private Context context;
    private DBHelper HabitDBHelper;

    public HabitDao(Context context) {
        this.context = context;
        HabitDBHelper = new DBHelper(context);
    }

    /**
     * 判断表中是否有数据
     */
    public boolean isDataExist(){
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = HabitDBHelper.getReadableDatabase();
            // select count(Id) from Orders
            cursor = db.query(HabitDBHelper.TABLE_NAME, new String[]{"COUNT(h_Id)"}, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

    /**
     * 初始化数据
     */
    public void initTable(){
        SQLiteDatabase db = null;

        try {
            db = HabitDBHelper.getWritableDatabase();
            db.beginTransaction();

            db.execSQL("insert into " + HabitDBHelper.TABLE_NAME + " (h_Id, h_thing, h_color, h_time, h_text, h_OK) values (1, '吃饭', 'gray', 1, '加油',0)");
            db.execSQL("insert into " + HabitDBHelper.TABLE_NAME + " (h_Id, h_thing, h_color, h_time, h_text, h_OK) values (2, '睡觉', 'blue', 2, '加油',0)");
            db.execSQL("insert into " + HabitDBHelper.TABLE_NAME + " (h_Id, h_thing, h_color, h_time, h_text, h_OK) values (3, '阅读', 'red', 3, '加油',0)");
            db.execSQL("insert into " + HabitDBHelper.TABLE_NAME + " (h_Id, h_thing, h_color, h_time, h_text, h_OK) values (4, '打篮球', 'yellow', 2, '加油',1)");
            db.execSQL("insert into " + HabitDBHelper.TABLE_NAME + " (h_Id, h_thing, h_color, h_time, h_text, h_OK) values (5, '学习新技能', 'black', 1, '加油',1)");
            db.execSQL("insert into " + HabitDBHelper.TABLE_NAME + " (h_Id, h_thing, h_color, h_time, h_text, h_OK) values (6, '刷牙', '#32CD99', 6, '加油',0)");

            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * 执行自定义SQL语句
     */
    public void execSQL(String sql) {
        SQLiteDatabase db = null;

        try {
            if (sql.contains("select")){
                Toast.makeText(context, R.string.strUnableSql, Toast.LENGTH_SHORT).show();
            }else if (sql.contains("insert") || sql.contains("update") || sql.contains("delete")){
                db = HabitDBHelper.getWritableDatabase();
                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
                Toast.makeText(context, R.string.strSuccessSql, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, R.string.strErrorSql, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * 查询数据库中所有数据
     */
    public List<Habit> getAllDate(){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = HabitDBHelper.getReadableDatabase();
            // select * from Habit
            cursor = db.query(HabitDBHelper.TABLE_NAME, Habit_COLUMNS, null, null, null, null, null);

            if (cursor.getCount() > 0) {
                List<Habit> habitList = new ArrayList<Habit>(cursor.getCount());
                while (cursor.moveToNext()) {
                    habitList.add(parseOrder(cursor));
                }
                return habitList;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    /**
     * 新增一条数据
     */
    public boolean insertDate(Habit habit){
        SQLiteDatabase db = null;

        try {
            db = HabitDBHelper.getWritableDatabase();
            db.beginTransaction();

            //insert into Orders(Id, CustomName, OrderPrice, Country) values (7, "Jne", 700, "China");
            ContentValues contentValues = new ContentValues();
            contentValues.put("h_Id", habit.getH_Id());
            contentValues.put("h_thing", habit.getH_thing());
            contentValues.put("h_color", habit.getH_color());
            contentValues.put("h_time", habit.getH_time());
            contentValues.put("h_text", habit.getH_text());
            contentValues.put("h_OK", habit.getH_OK());

            db.insertOrThrow(HabitDBHelper.TABLE_NAME, null, contentValues);

            db.setTransactionSuccessful();
            return true;
        }catch (SQLiteConstraintException e){
            Toast.makeText(context, "主键重复", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 删除一条数据  此处删除Id的数据
     */
    public boolean deleteHabit(int id) {
        SQLiteDatabase db = null;

        try {
            db = HabitDBHelper.getWritableDatabase();
            db.beginTransaction();

            db.delete(HabitDBHelper.TABLE_NAME, "h_Id = ?", new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 修改一条数据
     */
    public boolean updateHOk(int id,int time){
        SQLiteDatabase db = null;
        try {
            db = HabitDBHelper.getWritableDatabase();
            db.beginTransaction();
            time++;

            ContentValues cv = new ContentValues();
            cv.put("h_OK", 1);
            cv.put("h_time",time);

            db.update(HabitDBHelper.TABLE_NAME,
                    cv,
                    "h_Id = ?",
                    new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
            return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }
    /**
     * 修改一条数据
     */
    public boolean updateTime(){
        SQLiteDatabase db = null;
        try {
            db = HabitDBHelper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("update "+HabitDBHelper.TABLE_NAME+" set h_Ok = 0 where h_Id > 0");
            db.setTransactionSuccessful();
            return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 数据查询  最大Id
     */
    public int getMaxId(){
        int id = -1;
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = HabitDBHelper.getReadableDatabase();

            cursor = db.query(HabitDBHelper.TABLE_NAME,
                    Habit_COLUMNS,
                    null,
                    null,
                    null, null, null);
            if (cursor.getCount() > 0){
                if (cursor.moveToLast()) {
                    id = cursor.getInt(cursor.getColumnIndex("h_Id"));
                }
            }
            id++;
            return id;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
            return id;
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 将查找到的数据转换成Order类
     */
    private Habit parseOrder(Cursor cursor){
        Habit habit = new Habit();
        habit.setH_Id(cursor.getInt(cursor.getColumnIndex("h_Id")));
        habit.setH_thing(cursor.getString(cursor.getColumnIndex("h_thing")));
        habit.setH_color(cursor.getString(cursor.getColumnIndex("h_color")));
        habit.setH_time(cursor.getInt(cursor.getColumnIndex("h_time")));
        habit.setH_text(cursor.getString(cursor.getColumnIndex("h_text")));
        habit.setH_OK(cursor.getInt(cursor.getColumnIndex("h_OK")));
        return habit;
    }
}
