package life.majiang.community.test.javademo.multithreads.bank;

/**
 * @author: liu bin bin
 * @Date: 2021/3/4 14:19
 * @Description:
 */
public class BankSave implements Runnable{
    private Bank bank;

    public BankSave(Bank bank){
        this.bank=bank;
    }

    @Override
    public void run() {
        for (int i=0;i<30;i++){
            bank.saveMoney(100);
        }
    }
}
