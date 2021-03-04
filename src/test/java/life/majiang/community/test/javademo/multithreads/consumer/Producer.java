package life.majiang.community.test.javademo.multithreads.consumer;

/**
 * @author: liu bin bin
 * @Date: 2021/3/4 10:45
 * @Description:
 */
public class Producer implements Runnable {

    private Resource resource;

    public Producer(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true)
        resource.set("商品");
    }
}
