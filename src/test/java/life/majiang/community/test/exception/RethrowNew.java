package life.majiang.community.test.exception;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 11:19
 * @Description:  也有可能从一个已经捕获的违例重新“掷”出一个不同的违例。但假如这样做，会得到与使用fillInStackTrace() 类似的效果：
 * 与违例起源地有关的信息会全部丢失，我们留下的是与新的 throw 有关的信息。
 * 最后一个违例只知道自己来自 main()，而非来自 f()。注意 Throwable 在任何违例规范中都不是必需的。永远不必关心如何清除前一个违例，
 * 或者与之有关的其他任何违例。它们都属于用new 创建的、以内存堆为基础的对象，所以垃圾收集器会自动将其清除
 */
public class RethrowNew {
    public static void f() throws Exception {
        System.out.println("originating the exception in f()");
        throw new Exception("thrown from f()");
    }

    public static void main(String[] args) {
        try {
            f();
        } catch(Exception e) {
            System.out.println("Caught in main, e.printStackTrace()");
            e.printStackTrace();
            throw new NullPointerException("from main");
        }
    }

}
