package com.lcz.utilslib.Encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    //传入的字符串md5
    public static String md5(String str){
        String md5_str = "";
        if(str != null && str.length() > 0)
        {
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
                byte[] bytes = md5.digest(str.getBytes());
                String result = "";
                for(byte bt:bytes){
                    String tmp = Integer.toHexString(bt & 0xFF);
                    if(tmp.length() == 1){
                        tmp = "0"+tmp;
                    }
                    result += tmp;
                }
                md5_str = result;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return md5_str;
    }

    //把file进行md5转换
    public static String md5file(File file){
        if (file == null || !file.isFile() || !file.exists()) {
            return "";
        }
        FileInputStream in = null;
        String result = "";
        byte buffer[] = new byte[8192];
        int len;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            byte[] bytes = md5.digest();

            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null!=in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
