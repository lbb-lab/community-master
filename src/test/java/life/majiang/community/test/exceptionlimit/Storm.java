package life.majiang.community.test.exceptionlimit;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 15:26
 * @Description:
 */
public interface Storm {
    void event() throws RainedOut;
    void rainHard() throws RainedOut;
}
