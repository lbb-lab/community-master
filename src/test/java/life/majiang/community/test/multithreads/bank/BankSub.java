package life.majiang.community.test.multithreads.bank;

/**
 * @author: liu bin bin
 * @Date: 2021/3/4 14:23
 * @Description:
 */
public class BankSub implements Runnable{
    private Bank bank;

    public BankSub(Bank bank){
        this.bank = bank;
    }

    @Override
    public void run() {
        for (int i=0;i<50;i++){
            bank.subMoney(70);
        }
    }
}
