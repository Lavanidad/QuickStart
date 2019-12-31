package com.deepspring.libcommon.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.deepspring.libcommon.common.ContextProvider;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * SharedPreferences 缓存管理者
 *
 * @author liyushen
 */
public class SPManager {
    private static String FILE_NAME = "shared_fnlib";
    private final SharedPreferences preferences;
    private final Editor editor;
    private static SPManager instance;
    private Map<String, Object> defaultValues;
    private Map<String, Object> resetValues;

    public static SPManager instance() {
        if (instance == null) {
            instance = new SPManager();
        }
        return instance;
    }

    @SuppressLint("CommitPrefEdits")
    private SPManager() {
        defaultValues = new HashMap<String, Object>();
        resetValues = new HashMap<String, Object>();
        preferences = ContextProvider.getInstance().getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public SharedPreferences getSharedPreferences() {
        return preferences;
    }

    public Editor getEditor() {
        return editor;
    }

    /**
     * 获取所有得的sharepreferences
     *
     * @return 所有key, value
     */
    public Map<String, Object> getDefaultValues() {
        return Collections.unmodifiableMap(defaultValues);
    }

    /**
     * 获取所有启动软件需要重新设置的值
     *
     * @return key, value集合
     */
    public Map<String, Object> getResetValues() {
        return Collections.unmodifiableMap(resetValues);
    }

    public void resetValue(String key) {
        resetValues.put(key, defaultValues.get(key));
    }

    /**
     * 获取String类型
     *
     * @param key
     * @return value
     */
    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public String getString(String key, String value) {
        return preferences.getString(key, value);
    }

    /**
     * 设置String类型
     *
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 设置Float类型
     *
     * @param key
     * @param value
     */
    public void setFloat(String key, Float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public Float getFloat(String key) {
        return preferences.getFloat(key, 0);
    }

    /**
     * 获取int类型
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public int getInt(String key, int defaultInt) {
        return preferences.getInt(key, defaultInt);
    }

    /**
     * 设置int类型
     *
     * @param key
     * @param value
     */
    public void setInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 获取long类型
     *
     * @param key
     * @return
     */
    public long getLong(String key) {
        return preferences.getLong(key, 0);
    }

    /**
     * 设置long类型
     *
     * @param key
     * @param value
     */
    public void setLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 获取boolean类型
     *
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    /**
     * 获取boolean类型
     *
     * @param key
     * @return
     */
    public boolean getBooleanByCustomer(String key) {
        return preferences.getBoolean(key, false);
    }

    /**
     * 设置boolean类型
     *
     * @param key
     * @param value
     */
    public void setBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 通过key获取文件路径并转换成file对象
     *
     * @param key
     * @return
     */
    public File getFile(String key) {
        return new File(preferences.getString(key, ""));
    }

    /**
     * 设置文件转换成路径
     *
     * @param key
     * @param value
     */
    public void setFile(String key, File value) {
        editor.putString(key, value.getAbsolutePath());
        editor.commit();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return preferences.contains(key);
    }

    /**
     * 移除某个值
     *
     * @param key
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有的值
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public void put(String key, Object object) {
        Editor editor = preferences.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return preferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return preferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return preferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return preferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return preferences.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return preferences.getAll();
    }

    public void removeMorebykey(String likekey) {
        Map<String, ?> stringMap = getAll();
        for (String key : stringMap.keySet()) {
            if (key.startsWith(likekey)) {
                editor.remove(key);
                editor.commit();
            }
        }
    }


    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author liyushen
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

}