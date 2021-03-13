package life.majiang.community.test.keywordaccesspermissions;

import life.majiang.community.test.multithreads.ThreadTest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/11 10:00
 * @Description:
 */
public class TestClass {

    Integer money = 0;

    private ThreadTest threadTest;

    public void saveMoney(String name,Integer money){
        this.money+=money;
        System.out.println(name+"存了："+money+"￥");
    }

    private void subMoney(String name,Integer money){
        this.money-=money;
        System.out.println(name+"取了："+money+"￥");
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public ThreadTest getThreadTest() {
        return threadTest;
    }

    public void setThreadTest(ThreadTest threadTest) {
        this.threadTest = threadTest;
    }
}
