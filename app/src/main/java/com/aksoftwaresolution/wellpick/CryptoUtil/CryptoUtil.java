package com.aksoftwaresolution.wellpick.CryptoUtil;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {

   // Explicitly ECB mode
    private static final String KEY = "abulkhair123456@"; // 16 bytes for AES-128

    // Encrypt
    public String  encrypt(String plainText) throws Exception{

        byte[] plainbyte=plainText.getBytes("UTF-8");
        byte[] passwordbyte=KEY.getBytes("UTF-8");

       SecretKeySpec secretKeySpec=new SecretKeySpec(passwordbyte,"AES");
       Cipher cipher=Cipher.getInstance("AES");
       cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
       byte[] secretbyte=cipher.doFinal(plainbyte);

       String encodingString= Base64.encodeToString(secretbyte,Base64.DEFAULT);



      return encodingString;
    }


    public String decrypt(String encryptedText) throws Exception {


        byte[] secretbyte = Base64.decode(encryptedText, Base64.DEFAULT);

        /* Key  byte */
        byte[] passwordbyte = KEY.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(passwordbyte, "AES");

        /* Cipher */
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        //decryptedBytes
        byte[] decryptedBytes = cipher.doFinal(secretbyte);

        //decodString
        return new String(decryptedBytes, "UTF-8");
    }



}
