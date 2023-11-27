package hdq.rest.jvm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author daniel.hou
 * @date 2023/11/27 09:49
 * @Desc
 */
public class User {
    private String uid;
    private String uname;

    /**
     * 用户得uid就直接是uname md5 之后得就可以
     */
    public String getUid() {
        MessageDigest digest;
        try {
            digest = java.security.MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(this.uname.getBytes());
            return byteArrayToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            // TODO 自己处理吧;
            return null;
        }

    }

    private String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}