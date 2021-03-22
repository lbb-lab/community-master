package life.majiang.community.test.chapter12;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 9:32
 * @Description: toString 方法会在打印语句里自动调用，而 PassHandles 直接从 Object 继承，没有 toString 的重新定义。
 * 因此，这里会采用 toString 的Object 版本，打印出对象的类，接着是那个对象所在的位置（不是句柄，而是对象的实际存储位置）。输出结果如下：
 * p inside main(): PassHandles@1653748
 * h inside f() : PassHandles@1653748
 * 可以看到，无论p 还是h 引用的都是同一个对象。这比复制一个新的PassHandles 对象有效多了，使我们能将一个参数发给一个方法。
 * 但这样做也带来了另一个重要的问题。
 */
public class PassHandles {
    static void f(PassHandles h) {
        System.out.println("h inside f(): " + h);
    }
    public static void main(String[] args) {
        PassHandles p = new PassHandles();
        System.out.println("p inside main(): " + p);
        f(p);
    }

}
