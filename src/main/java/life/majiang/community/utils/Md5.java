package life.majiang.community.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * @author zehuang
 * @version 1.0
 * @data 创建时间：2017/11/24
 * @parameter
 * @return
 * @since JDK8
 */
public class Md5 {
    private Md5() {
    }

    public static String genMd5(String message) {
        return DigestUtils.md5Hex(message);
    }

    public static String generateAccessSkey() {
        return DigestUtils.md5Hex(UUID.randomUUID().toString());
    }
}
