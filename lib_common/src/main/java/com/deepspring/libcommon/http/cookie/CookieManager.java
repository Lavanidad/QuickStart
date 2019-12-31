package com.deepspring.libcommon.http.cookie;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @author fzy
 * @date 2019/12/17.
 * description： cookie管理
 */
public class CookieManager implements CookieJar {

    private static volatile CookieManager instance;

    private final PersistentCookieStore persistentCookieStore;

    public static CookieManager getInstance() {
        if (instance == null) {
            synchronized (CookieManager.class) {
                if (instance == null) {
                    instance = new CookieManager();
                }
            }
        }
        return instance;
    }

    private CookieManager() {
        persistentCookieStore = new PersistentCookieStore();
    }


    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        persistentCookieStore.add(url,cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = persistentCookieStore.get(url);
        return persistentCookieStore.get(url);
    }

    public void clearAllCookie(){
        persistentCookieStore.removeAll();
    }
}
