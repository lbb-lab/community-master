package life.majiang.community.test.javademo.multithreads.consumer;

/**
 * @author: liu bin bin
 * @Date: 2021/3/4 10:45
 * @Description:
 */
public class ProducerLock implements Runnable {

    private ResourceLock resource;

    public ProducerLock(ResourceLock resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true)
        resource.set("商品");
    }
}
