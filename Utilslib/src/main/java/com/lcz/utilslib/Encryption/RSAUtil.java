package com.lcz.utilslib.Encryption;

import android.util.Log;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;

public class RSAUtil {

    private static RSAPublicKey mRsaPublicKey;
    private static RSAPrivateKey mRsaPrivateKey;
    private static byte[] result;
    private static Cipher cipher;
    static KeyFactory keyFactory;

    //1.初始化密钥
    public static void init() {

        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512); // 设置长度 最大值 6536
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            // RSA公钥
            mRsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            // RSA私钥
            mRsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            Log.d("RSA----->", "RSA公钥: " + mRsaPublicKey);
            Log.d("RSA----->", "RSA私钥: " + mRsaPrivateKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }



    //2.私钥加密、公钥解密 --- 加密
    public static String privateAdd(String srcRSA) {

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(mRsaPrivateKey.getEncoded());

        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec); // 最终使用的私钥
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey); // 加密模式
            result = cipher.doFinal(srcRSA.getBytes());
            return Arrays.toString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //3.私钥加密、公钥解密 --- 解密
    public static String publicopen() {

        try {
            keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(mRsaPublicKey.getEncoded());
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(result);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // 4.公钥加密、私钥解密 --- 加密
    public static String publicAdd(String srcRSA) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(mRsaPublicKey.getEncoded());
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey); // 加密
            result = cipher.doFinal(srcRSA.getBytes());
            return Arrays.toString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 5.公钥加密、私钥解密 --- 解密
    public static String privateopen() {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(mRsaPrivateKey.getEncoded());
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(result);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
