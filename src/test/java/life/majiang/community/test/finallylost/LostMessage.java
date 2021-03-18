package life.majiang.community.test.finallylost;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 16:13
 * @Description:一般情况下，Java 的违例实施方案都显得十分出色。不幸的是，它依然存在一个缺点。尽管违例指出程序里存在一个危机，
 *   而且绝不应忽略，但一个违例仍有可能简单地“丢失”。在采用 finally 从句的一种特殊配置下，便有可能发生这种情况：
 *   下面便是f（）产生的异常丢失了
 *
 *   可以看到，这里不存在 VeryImportantException （非常重要的违例）的迹象，它只是简单地被 finally 从句中的HoHumException 代替了。
 * 这是一项相当严重的缺陷，因为它意味着一个违例可能完全丢失。而且就象前例演示的那样，这种丢失显得
 * 非常“自然”，很难被人查出蛛丝马迹。而与此相反，C++里如果第二个违例在第一个违例得到控制前产生， 就会被当作一个严重的编程错误处理。
 * 或许Java 以后的版本会纠正这个问题（上述结果是用Java 1.1 生成的）。
 */
public class LostMessage {
    void f() throws VeryImportantException{
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }

    public static void main(String[] args) throws Exception {
        LostMessage lm = new LostMessage();
        try {
            lm.f();
        } finally {
            lm.dispose();
        }
    }

}
