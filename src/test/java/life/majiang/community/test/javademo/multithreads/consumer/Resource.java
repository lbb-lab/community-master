package life.majiang.community.test.javademo.multithreads.consumer;

/**
 * @author: liu bin bin
 * @Date: 2021/3/4 10:41
 * @Description:
 */
public class Resource {
     private String name;
     private Integer count = 0;
     private boolean flag = false;


     public synchronized void set(String name){
         while (flag){
             try {
                 wait();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         System.out.println(Thread.currentThread().getName()+"..."+"生产者"+"  "+name+"---"+count++);
         flag = true;
         notifyAll();
     }

     public synchronized void out(String name){
         while (!flag){
             try {
                 wait();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         System.out.println(Thread.currentThread().getName()+"..."+"消费者"+name+"--"+count);
         flag = false;
         notifyAll();
     }
}
