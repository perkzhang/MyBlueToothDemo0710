package com.bluetooth.perk.des;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by perk on 2016/7/28.
 */
public class DesUtils {


    //加密
    public static byte[] encrypt(byte[] dataSource, String password) {
        try {
            SecureRandom e = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKey secureKey = keyFactory.generateSecret(desKey);//从一个指定的规格生成一个密钥
            Cipher cipher = Cipher.getInstance("DES");//Cipher对象实际完成加密操作

            cipher.init(1, secureKey, e);//用密匙初始化Cipher对象 ENCRYPT_MODE：1（加密模式）
            return cipher.doFinal(dataSource);//现在，获取数据并加密，正式执行加密操作
        } catch (Throwable var7) {
            var7.printStackTrace();
            return null;
        }
    }

    //解密
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secureKey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, secureKey, random);
        return cipher.doFinal(src);
    }
}
