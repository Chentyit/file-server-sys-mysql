package cn.chentyit.fileserversystem.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author Chentyit
 * @Date 2020/5/15 17:57
 * @Description:
 */
public class Md5Util {

    public static String getMd5(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        StringBuffer md5 = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] dataBytes = new byte[2048];

        int read = 0;
        while ((read = inputStream.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, read);
        }
        byte[] digests = md.digest();

        for (byte digest : digests) {
            md5.append(Integer.toString((digest & 0xff) + 0x100, 16).substring(1));
        }
        return md5.toString();
    }
}
