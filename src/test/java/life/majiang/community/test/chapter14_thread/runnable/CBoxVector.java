package life.majiang.community.test.chapter14_thread.runnable;

import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/23 0:39
 * @Description:
 */
public class CBoxVector extends Vector implements Runnable {
    private Thread t;
    private int pause;

    public CBoxVector(int pause) {
        this.pause = pause;
        t = new Thread(this);
    }

    public void go() {
        t.start();
    }

    public void run() {
        while(true) {
            int i = (int)(Math.random() * size());
            ((CBox2)elementAt(i)).nextColor();
            try {
                t.sleep(pause);
            } catch(InterruptedException e) {}
        }
    }

}
