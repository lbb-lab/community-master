package life.majiang.community.test.chapter14_thread.threadgroup;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 23:51
 * @Description:
 */
public class TestThread2 extends Thread {
    TestThread2(ThreadGroup g, String name) {
        super(g, name); start();
    }

    public void run() {
        ThreadGroup g = getThreadGroup().getParent().getParent();
        g.list();
        Thread[] gAll = new Thread[g.activeCount()];
        g.enumerate(gAll);
        for(int i = 0; i < gAll.length; i++) {
            gAll[i].setPriority(Thread.MIN_PRIORITY);
            ((TestThread1)gAll[i]).f();
//            gAll[i].f();
        }
        g.list();
    }

}
