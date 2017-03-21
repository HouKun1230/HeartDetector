package com.example.android.wearablemessageapiexample;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by kun on 2017-03-15.
 */

public class encrypt {

    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec ex = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, skeySpec, ex);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            // System.out.println("encrypted string: " + Base64.getEncoder().encode(encrypted));
            return Base64.encodeToString(encrypted,Base64.DEFAULT);
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec ex = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(2, skeySpec, ex);
            byte[] original = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT));
            return new String(original);
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }
}
