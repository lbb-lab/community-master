package life.majiang.community.test.abstractinterface;

/**
 * @author: liu bin bin
 * @Date: 2021/3/14 2:11
 * @Description:
 */
public class Adventure {
    static void t(CanFight x){x.figth();}
    static void u(CanSwim x){x.swim();}
    static void w(ActionCharacter x){x.figth();}
    static void v(CanFly x){x.fly();}

    public static void main(String[] args){
        Hero i = new Hero(); //hero里面实现了canfigth但是没有实现里面的方法，为什么不报错？因为继承的ActionCharacter里面有这个方法默认了
        t(i);
        u(i);
        v(i);
        w(i);
    }

}
