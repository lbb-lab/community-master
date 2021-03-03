package life.majiang.community.utils;

import java.security.MessageDigest;

public class MD5Util {
    /**
     * 生成32位小写md5码
     *
     * @param source
     * @return
     */
    public static String encryption(String source) throws Exception {
        // 得到一个信息摘要器
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update((source).getBytes("UTF-8"));
        byte[] b = md5.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

}
