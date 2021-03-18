package life.majiang.community.test.finallylost;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 16:11
 * @Description:  一般情况下，Java 的违例实施方案都显得十分出色。不幸的是，它依然存在一个缺点。尽管违例指出程序里存在一个危机，
 * 而且绝不应忽略，但一个违例仍有可能简单地“丢失”。在采用 finally 从句的一种特殊配置下，便有可能发生这种情况：
 */
public class VeryImportantException extends Exception {
    public String toString() {
        return "A very important exception!";
    }
}
