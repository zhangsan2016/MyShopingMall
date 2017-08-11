package com.ldgd.com.myshopingmall.shoppingcart.fragment.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ldgd on 2017/5/6.
 */

public class CacheUtils {


    private static final String SP_NAME = "ldgd";

    /**
     * 得到缓存值
     *
     * @param context 上下文
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }


    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();

    }

    public static void putString(Context context, String key, String value) {

        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context mContext, String key) {
        String result = "";
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        result = sp.getString(key, "");
        return result;
    }


    /*public static void putString(Context context, String key, String value) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ///mnt/sdcard/beijingnews/files/llkskljskljklsjklsllsl
            try {

                //llkskljskljklsjklsllsl
                String fileName = MD5Encoder.encode(key);

                ///mnt/sdcard/beijingnews/files/llkskljskljklsjklsllsl
                File file = new File(Environment.getExternalStorageDirectory() + "/beijingnews/files", fileName);

                //mnt/sdcard/beijingnews/files
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    //创建目录
                    parentFile.mkdirs();
                }

                if (!file.exists()) {
                    file.createNewFile();
                }

                //保存文本数据
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(value.getBytes());
                fileOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e("文本数据缓存失败");
            }


        } else {
            SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
            sp.edit().putString(key, value).commit();

        }


    }

    public static String getString(Context context, String key) {

        String result = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ///mnt/sdcard/beijingnews/files/llkskljskljklsjklsllsl
            try {

                //llkskljskljklsjklsllsl
                String fileName = MD5Encoder.encode(key);

                ///mnt/sdcard/beijingnews/files/llkskljskljklsjklsllsl
                File file = new File(Environment.getExternalStorageDirectory() + "/beijingnews/files", fileName);


                if (file.exists()) {
                    FileInputStream is = new FileInputStream(file);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    byte[] buffer = new byte[1024];
                    int length = 0;
                    while ((length = is.read(buffer)) != -1) {
                        stream.write(buffer, 0, length);
                    }

                    is.close();
                    stream.close();

                    result = stream.toString();

                }


            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e("文本数据缓存获取失败");
            }


        } else {
            SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
            result = sp.getString(key, "");

        }
        return result;

    }*/

}
