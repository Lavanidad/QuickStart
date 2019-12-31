package com.deepspring.libcommon.common;

import java.io.File;

/**
 * @author fzy
 * @date 2019/12/17.
 * description：
 */
public class BaseConstants {

    /**
     * Path 路径
     */
    public static final String PATH_DATA = ContextProvider.getInstance().getContext().getCacheDir().getAbsolutePath() + File.separator + "data";
    /**
     * 网络缓存路径
     */
    public static final String PATH_NET_CACHE = PATH_DATA + "/netCache";

    /**
     * 应用缓存路径
     */
    public static final String PATH_CACHE_DATA = ContextProvider.getInstance().getContext().getCacheDir().getAbsolutePath();


    /**
     * SP KEY
     */
    public static final String SP_KEY_EQUIPMENTID = "equipmentId";
}
