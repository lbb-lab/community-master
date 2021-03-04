package life.majiang.community.test.javademo.multithreads;

import life.majiang.community.test.javademo.multithreads.consumer.ConsumerLock;
import life.majiang.community.test.javademo.multithreads.consumer.ProducerLock;
import life.majiang.community.test.javademo.multithreads.consumer.ResourceLock;

/**
 * @author: liu bin bin
 * @Date: 2021/3/4 10:50
 * @Description:
 */
public class ThreadTest {
    public static void main(String[] args) {
//        //生产消费_synchronized
//         Resource resource = new Resource();
//         Producer producer = new Producer(resource);
//         Consumer consumer = new Consumer(resource);
//         Thread t1 = new Thread(producer);
//         Thread t2 = new Thread(consumer);
//         Thread t3 = new Thread(producer);
//         Thread t4 = new Thread(consumer);
//         t1.start();
//         t2.start();
//         t3.start();
//         t4.start();

//        //银行
//        Bank bank = new Bank();
//        BankSave save = new BankSave(bank);
//        BankSub sub = new BankSub(bank);
//        Thread t1 = new Thread(save);
//        Thread t2 = new Thread(sub);
//        Thread t3 = new Thread(save);
//        Thread t4 = new Thread(sub);
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();

        //生产消费——Lock
        ResourceLock res = new ResourceLock();
        ProducerLock pro = new ProducerLock(res);
        ConsumerLock con = new ConsumerLock(res);
        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(con);
        Thread t3 = new Thread(pro);
        Thread t4 = new Thread(con);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

}
