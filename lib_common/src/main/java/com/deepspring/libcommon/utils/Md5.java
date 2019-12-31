package com.deepspring.libcommon.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author baoy
 * @version 1.0
 *          Create by 2017/7/18 下午9:56
 */
public class Md5 {

    public static String md5(String val) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(val.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 should not be supported!", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 should not be supported!", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }
}
