package life.majiang.community.test.staticdatainitialization;

/**
 * @author: liu bin bin
 * @Date: 2021/3/12 14:41
 * @Description:
 */
public class Cup {
    Cup(int marker){
       System.out.println("Cup("+marker+")");
    }

    void f(int marker){
        System.out.println("f("+marker+")");
    }
}
