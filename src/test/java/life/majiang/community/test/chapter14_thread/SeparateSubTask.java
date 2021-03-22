package life.majiang.community.test.chapter14_thread;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 23:30
 * @Description:
 */
public class SeparateSubTask extends Thread{
    private int count = 0;
    private Counter2 c2;
    private boolean runFlag = true;

    public SeparateSubTask(Counter2 c2) {
        this.c2 = c2;
        start();
    }

    public void invertFlag() {
        runFlag = !runFlag;
    }

    public void run() {
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e){}
            if(runFlag)
                c2.t.setText(Integer.toString(count++));
        }
    }

}

