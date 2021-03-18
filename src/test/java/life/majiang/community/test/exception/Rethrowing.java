package life.majiang.community.test.exception;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 11:04
 * @Description:    若只是简单地重新掷出当前违例，我们打印出来的、与printStackTrace()内的那个违例有关的信息会与违例的起源地对应，
 * 而不是与重新掷出它的地点对应。若想安装新的堆栈跟踪信息，可调用fillInStackTrace() ，它会返回一个特殊的违例对象。
 *        由于使用的是fillInStackTrace() ，第18 行成为违例的新起点。
 * 针对g()和main()，Throwable 类必须在违例规格中出现，因为fillInStackTrace() 会生成一个 Throwable 对象的句柄。
 * 由于 Throwable 是Exception 的一个基础类，所以有可能获得一个能够“掷”出的对象（具有Throwable 属性），但却并非一个 Exception（违例）。
 * 因此，在main()中用于 Exception 的句柄可能丢失自己的目标。为保证所有东西均井然有序，编译器强制Throwable 使用一个违例规范
 */
public class Rethrowing {
    public static void f() throws Exception {
        System.out.println("originating the exception in f()");
        throw new Exception("thrown from f()");
    }

    public static void g() throws Throwable {
        try {
            f();
        } catch(Exception e) {
            System.out.println("Inside g(), e.printStackTrace()");
            e.printStackTrace();
//            throw e; // 17
         throw e.fillInStackTrace(); // 18
        }
    }
    public static void main(String[] args) throws Throwable {
        try {
            g();
        } catch(Exception e) {
            System.out.println("Caught in main, e.printStackTrace()");
            e.printStackTrace();
        }
    }

}
