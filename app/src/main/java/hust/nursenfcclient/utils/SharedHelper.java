package hust.nursenfcclient.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by admin on 2016/3/23.
 */
public class SharedHelper {
    private static final String SHARED_NAME = "mall_guide";
    private static final int    SHARED_MODE = Context.MODE_PRIVATE;

    public static final String CURRENT_USER_NAME = "current_user_name"; // 当前用户名

    // 查询是否首次登录
    public static final String ISLOGED = "isLoged";


    public static final String DEFAULT_VALUE = "NULL_VALUE";

    private SharedPreferences shared;

    private SharedHelper(Context context) {
        shared = context.getApplicationContext().getSharedPreferences(SHARED_NAME, SHARED_MODE);
    }

    private volatile static SharedHelper instance = null;

    // 返回实例
    public static SharedHelper getSharedHelper(Context context) {
        if (instance == null) {
            synchronized (SharedHelper.class) {
                if (instance == null) {
                    instance = new SharedHelper(context);
                }
            }
        }
        return instance;
    }

    // 查询
    public boolean getBoolean(String key, boolean default_value) {
        return shared.getBoolean(key, default_value);
    }

    public boolean getBoolean(String key) {
        return shared.getBoolean(key, false);
    }

    public String getString(String key, String default_value) {
        return shared.getString(key, default_value);
    }

    public String getString(String key) {
        return shared.getString(key, DEFAULT_VALUE);
    }

    public int getInt(String key, Integer default_value) {
        return shared.getInt(key, default_value);
    }

    public int getInt(String key) {
        return shared.getInt(key, 0);
    }

    private static final String TYPE_STRING = "java.lang.String";
    private static final String TYPE_INTEGER = "java.lang.Integer";
    private static final String TYPE_BOOLEAN = "java.lang.Boolean";

    public SharedPreferences.Editor edit() {
        return shared.edit();
    }

    public void put(String key, String value) {
        SharedPreferences.Editor editor = shared.edit();
        editor.putString(key, value);
        editor.commit();
    }

    // 插入数据
    public void put(UnitData<?> data) {
        SharedPreferences.Editor editor = shared.edit();
        putData(data, editor);
        editor.commit();
    }

    public void putAll(ArrayList<UnitData> datas) {
        SharedPreferences.Editor editor = shared.edit();

        for (UnitData<?> data : datas) {
            putData(data, editor);
        }
        editor.commit();
    }

    private void putData(final UnitData<?> data, final SharedPreferences.Editor editor) {
        switch (data.clazz.getName()) {
            case TYPE_STRING:
                editor.putString(data.key, (String)data.value);
                break;
            case TYPE_INTEGER:
                editor.putInt(data.key, (Integer) data.value);
                break;
            case TYPE_BOOLEAN:
                editor.putBoolean(data.key, (Boolean) data.value);
                break;
        }
    }

    public static class UnitData<T> {
        String key;
        T value;
        Class<?> clazz;

        public UnitData(String key, T value) {
            this.key = key;
            this.value = value;
            clazz = value.getClass();
        }
    }
}
