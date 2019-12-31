package com.deepspring.libcommon.http.cookie;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;


import com.deepspring.libcommon.common.ContextProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * @author fzy
 * @date 2019/12/17.
 * description：Cookie缓存持久化实现类
 */
public class PersistentCookieStore {

    private static final String TAG = PersistentCookieStore.class.getSimpleName();

    private static final String COOKIE_PREFS = "CookiePrefsFile";
    private static final String COOKIE_NAME_PREFIX = "cookie_";

    //存放cookie
    private final Map<String, ConcurrentHashMap<String, Cookie>> mCookiesMap;
    //cookie 持久化
    private final SharedPreferences mCookiesPrefs;

    public PersistentCookieStore() {
        mCookiesPrefs = ContextProvider.getInstance().getContext().getSharedPreferences(COOKIE_PREFS, 0);
        mCookiesMap = new HashMap<>();

        Map<String, ?> prefsAll = mCookiesPrefs.getAll();
        for (Map.Entry<String, ?> entry : prefsAll.entrySet()) {
            if (null != entry.getValue() && !((String) entry.getValue()).startsWith(COOKIE_NAME_PREFIX)) {
                String[] cookieNames = TextUtils.split((String) entry.getValue(), ",");
                for (String cookieName : cookieNames) {
                    String encodedCookie = mCookiesPrefs.getString(COOKIE_NAME_PREFIX + cookieName, null);
                    if (encodedCookie != null) {
                        Cookie decodedCookie = decodedCookie(encodedCookie);
                        if (decodedCookie != null) {
                            if (!mCookiesMap.containsKey(entry.getKey())) {
                                //如果mCookies 不存在则 保存 cookie key value
                                mCookiesMap.put(entry.getKey(), new ConcurrentHashMap<String, Cookie>());
                            }
                            mCookiesMap.get(entry.getKey()).put(cookieName, decodedCookie);
                        }
                    }
                }
            }
        }
    }


    public void add(HttpUrl httpUrl, List<Cookie> cookies) {
        if (null != cookies && cookies.size() > 0) {
            for (Cookie cookie : cookies) {
                add(cookie);
            }
        }
    }

    private void add(Cookie cookie) {
        //应该以 Cookie 的 domain 来做缓存 Key 才合适，解决无法子跨域名使用问题
        if (!mCookiesMap.containsKey(cookie.domain())) {
            mCookiesMap.put(cookie.domain(), new ConcurrentHashMap<String, Cookie>());
        }
        if (cookie.expiresAt() > System.currentTimeMillis()) {
            mCookiesMap.get(cookie.domain()).put(cookie.name(), cookie);
        } else {
            mCookiesMap.get(cookie.domain()).remove(cookie.domain());
        }
        //cookie 数据持久化本地
        SharedPreferences.Editor prefsWriter = mCookiesPrefs.edit();
        prefsWriter.putString(cookie.domain(), TextUtils.join(",", mCookiesMap.get(cookie.domain()).keySet()));
        prefsWriter.putString(COOKIE_NAME_PREFIX + cookie.name(), encodedCookie(new SerializableHttpCookie(cookie)));
        prefsWriter.apply();
    }

    public List<Cookie> get(HttpUrl url) {
        ArrayList<Cookie> ret = new ArrayList<>();
        for (String key : mCookiesMap.keySet()) {
            if (url.host().contains(key)) {
                ret.addAll(mCookiesMap.get(key).values());
            }
        }
        return ret;
    }

    /**
     * 把 cookie 序列化成 string
     *
     * @param serializableHttpCookie
     * @return
     */
    private String encodedCookie(SerializableHttpCookie serializableHttpCookie) {
        if (serializableHttpCookie == null) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(serializableHttpCookie);
        } catch (IOException e) {
            Log.d(TAG, "IOException in encodedCookie", e);
            e.printStackTrace();
        }
        return byteArrayToHexString(bos.toByteArray());
    }

    /**
     * 将 string 反序列化成 cookie
     *
     * @param encodeCookie
     * @return
     */
    private Cookie decodedCookie(String encodeCookie) {
        byte[] bytes = hexStringToByteArray(encodeCookie);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Cookie cookie = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            cookie = ((SerializableHttpCookie) ois.readObject()).getCookie();
        } catch (IOException e) {
            Log.d(TAG, "IOException in decodeCookie", e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.d(TAG, "ClassNotFoundException in decodeCookie", e);
            e.printStackTrace();
        }
        return cookie;
    }

    /**
     * Converts hex values from strings to byte arra
     * 十六进制字符串转二进制数组
     *
     * @param encodeCookie
     * @return
     */
    private byte[] hexStringToByteArray(String encodeCookie) {
        int length = encodeCookie.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(encodeCookie.charAt(i), 16) << 4) + Character.digit(encodeCookie.charAt(i + 1), 16));
        }
        return bytes;
    }

    /**
     * 二进制数组转十六进制字符串
     * Using some super basic byte array <-> hex conversions so we don't have to rely on any
     * large Base64 libraries. Can be overridden if you like!
     *
     * @param bytes
     * @return
     */
    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 0xff;
            if (v < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(v));
        }
        return stringBuilder.toString().toUpperCase(Locale.US);
    }

    public synchronized boolean removeAll() {
        SharedPreferences.Editor prefsWriter = mCookiesPrefs.edit();
        prefsWriter.clear();
        prefsWriter.apply();
        mCookiesMap.clear();
        return true;
    }
}
