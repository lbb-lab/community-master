package life.majiang.community.test.chapter14_thread.threadgroup;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 23:50
 * @Description:
 */
public class TestThread1 extends Thread{
    private int i;
    TestThread1(ThreadGroup g, String name) {
        super(g, name);
    }
    void f() {
        i++; // modify this thread
        System.out.println(getName() + " f()");
    }

}
