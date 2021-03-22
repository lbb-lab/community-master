package life.majiang.community.test.chapter12;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 9:43
 * @Description:  此时最直接的一个解决办法就是干脆不这样做：不要有意将多个句柄指向同一个作用域内的同一个对象。这
 * 样做可使代码更易理解和调试。然而，一旦准备将句柄作为一个自变量或参数传递——这是Java 设想的正常方法——别名问题
 * 就会自动出现，因为创建的本地句柄可能修改“外部对象”（在方法作用域之外创建的对  象）。下面是一个例子
 *
 * 方法改变了自己的参数——外部对象。一旦遇到这种情况，必须判断它是否合理，用户是否愿意这样，以及 是不是会造成问题。
 * 通常，我们调用一个方法是为了产生返回值，或者用它改变为其调用方法的那个对象的状态（方法其实就是
 * 我们向那个对象“发一条消息”的方式）。很少需要调用一个方法来处理它的参数；这叫作利用方法的“副作用”（Side  Effect）。
 * 所以倘若创建一个会修改自己参数的方法，必须向用户明确地指出这一情况，并警告使用那个方法可能会有的后果以及它的潜在威胁。
 * 由于存在这些混淆和缺陷，所以应该尽量避免改变参数。
 * 若需在一个方法调用期间修改一个参数，且不打算修改外部参数，就应在自己的方法内部制作一个副本，从 而保护那个参数。
 * 本章的大多数内容都是围绕这个问题展开的。
 */
public class Alias2 {
    int i;
    Alias2(int ii) { i = ii; }
    static void f(Alias2 handle) {
        handle.i++;
    }

    public static void main(String[] args) {
        Alias2 x = new Alias2(7);
        System.out.println("x: " + x.i);
        System.out.println("Calling f(x)");
        f(x);
        System.out.println("x: " + x.i);
    }

}
