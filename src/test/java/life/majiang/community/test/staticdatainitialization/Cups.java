package life.majiang.community.test.staticdatainitialization;

/**
 * @author: liu bin bin
 * @Date: 2021/3/12 14:43
 * @Description:
 */
public class Cups {
    static Cup c1;
    static Cup c2;

    static {
        c1 = new Cup(1);
        c2 = new Cup(2);
    }

    Cups(){
       System.out.println("Cups()");
    }

}
