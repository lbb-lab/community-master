package life.majiang.community.test.javademo.multithreads.bank;

/**
 * @author: liu bin bin
 * @Date: 2021/3/4 14:04
 * @Description:
 */
public class Bank {

    private int count = 0;

    /**
     * 存钱
     * @param money
     */
    public void saveMoney(int money){
        synchronized (this){
            notifyAll();
            count+=money;
            System.out.println(Thread.currentThread().getName()+"---存钱成功，余额为："+count+"  存了："+money);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取钱
     * @param money
     */
    public void subMoney(int money){
        synchronized (this){
            if (count-money<0){
                System.out.println("余额不足!");
                return;
            }
            notifyAll();
            count-=money;
            System.out.println(Thread.currentThread().getName()+"---取钱成功，余额为："+count+"  取了："+money);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
