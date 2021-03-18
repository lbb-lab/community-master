package life.majiang.community.test.exception;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 12:37
 * @Description:
 */
public class MyException2 extends Exception{
    public MyException2() {}
    public MyException2(String msg) { super(msg);
    }
    public MyException2(String msg, int x) { super(msg);
        i = x;
    }

    public int val() { return i; }

    private int i;

}
