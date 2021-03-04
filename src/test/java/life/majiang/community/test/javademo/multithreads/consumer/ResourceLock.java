package life.majiang.community.test.javademo.multithreads.consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: liu bin bin
 * @Date: 2021/3/4 10:41
 * @Description:
 */
public class ResourceLock {
    private String name;
    private Integer count = 0;
    private boolean flag = false;
    private Lock lock = new ReentrantLock();
    private Condition condition_pro = lock.newCondition();
    private Condition condition_con = lock.newCondition();

    public  void set(String name) {
        lock.lock();
        try {
            while (flag){
                try {
                    condition_pro.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"..."+"生产者"+"  "+name+"---"+count++);
            flag = true;
            condition_con.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public  void out(String name) {
        lock.lock();
        try {
            while (!flag){
                try {
                    condition_con.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"..."+"消费者"+name+"--"+count);
            flag = false;
            condition_pro.signalAll();
        } finally {
           lock.unlock();
        }
    }
}
