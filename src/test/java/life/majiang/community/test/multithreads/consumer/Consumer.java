package life.majiang.community.test.multithreads.consumer;

/**
 * @author: liu bin bin
 * @Date: 2021/3/4 10:46
 * @Description:
 */
public class Consumer implements Runnable{
   private Resource resource;

   public Consumer(Resource resource){
       this.resource=resource;
   }

    @Override
    public void run() {
        while (true)
            resource.out("商品");
    }
}
