package life.majiang.community.test.keywordaccesspermissions;

/**
 * @author: liu bin bin
 * @Date: 2021/3/11 10:00
 * @Description:
 */
public class TestClass {

    Integer money = 0;

    public void saveMoney(String name,Integer money){
        this.money+=money;
        System.out.println(name+"存了："+money+"￥");
    }

    private void subMoney(String name,Integer money){
        this.money-=money;
        System.out.println(name+"取了："+money+"￥");
    }






}
