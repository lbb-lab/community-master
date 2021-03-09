package life.majiang.community.test.multithreads.consumer;

/**
 * @author: liu bin bin
 * @Date: 2021/3/4 10:46
 * @Description:
 */
public class ConsumerLock implements Runnable{
   private ResourceLock resource;

   public ConsumerLock(ResourceLock resource){
       this.resource=resource;
   }

    @Override
    public void run() {
        while (true)
            resource.out("商品");
    }
}
