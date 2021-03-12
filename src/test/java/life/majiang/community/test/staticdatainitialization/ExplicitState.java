package life.majiang.community.test.staticdatainitialization;

/**
 * @author: liu bin bin
 * @Date: 2021/3/12 14:45
 * @Description:
 */
public class ExplicitState {
    public static void main(String[] args){
        System.out.println("Inside main()");
        Cups.c1.f(99);
    }

    static Cups x = new Cups();
    static Cups y = new Cups();


}
