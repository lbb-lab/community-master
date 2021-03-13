package life.majiang.community.test.random;

/**
 * @author: liu bin bin
 * @Date: 2021/3/12 11:04
 * @Description:
 */
public class RandomTest {

    public static void main(String[] args){
        Thread t1 = new Thread(new RandomRunable("UPPER"));
        Thread t2 = new Thread(new RandomRunable("LOWER"));
        t1.start();
        t2.start();
    }

}
