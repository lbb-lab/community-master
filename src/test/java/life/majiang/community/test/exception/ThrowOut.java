package life.majiang.community.test.exception;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 11:16
 * @Description:      针对g()和main()，Throwable 类必须在违例规格中出现，因为fillInStackTrace() 会生成一个 Throwable 对象的句柄。
 * 由于 Throwable 是Exception 的一个基础类，所以有可能获得一个能够“掷”出的对象（具有Throwable 属性），但却并非一个 Exception（违例）。
 * 因此，在main()中用于 Exception 的句柄可能丢失自己的目标。为保证所有东西均井然有序，编译器强制Throwable 使用一个违例规范。举个例子来说，
 * 下述程序的违例便不会在main()中被捕获到：
 */
public class ThrowOut {

    public static void main(String[] args) throws Throwable {
        try {
            throw new Throwable();
        } catch(Exception e) {
            System.out.println("Caught in main()");
        }
    }

}
