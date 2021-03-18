package life.majiang.community.test.exception;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 12:30
 * @Description: 这里的关键是“extends Exception”，它的意思是：除包括一个 Exception 的全部含义以外，还有更多的含义。
 * 增加的代码数量非常少——实际只添加了两个构建器，对 MyException 的创建方式进行了定义。请记
 * 住，假如我们不明确调用一个基础类构建器，编译器会自动调用基础类默认构建器。在第二个构建器中，通 过使用 super 关键字，
 * 明确调用了带有一个 String 参数的基础类构建器。
 * 该程序输出结果如下：
 */
public class Inheriting {
    public static void f() throws MyException {
        System.out.println("Throwing MyException from f()");
        throw new MyException();
    }

    public static void g() throws MyException {
        System.out.println("Throwing MyException from g()");
        throw new MyException("Originated in g()");
    }

    public static void main(String[] args) {
        try {
            f();
        } catch(MyException e) {
            e.printStackTrace();
        }

        try {
            g();
        } catch(MyException e) {
            e.printStackTrace();
        }
    }

}
