package life.majiang.community.test.exception;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 12:19
 * @Description: 看起来似乎在传递进入一个方法的每个句柄中都必须检查 null（因为不知道调用者是否已传递了一个有效的句柄），
 * 这无疑是相当可怕的。但幸运的是，我们根本不必这样做——它属于 Java 进行的标准运行期检查的一部分。若对一个空句柄发出了调用，
 * Java 会自动产生一个NullPointerException 违例。所以上述代码在任何情况下都是多余的。
 * 这个类别里含有一系列违例类型。它们全部由 Java 自动生成，毋需我们亲自动手把它们包含到自己的违例规
 * 范里。最方便的是，通过将它们置入单独一个名为 RuntimeException 的基础类下面，它们全部组合到一起。这是一个很好的继承例子：
 * 它建立了一系列具有某种共通性的类型，都具有某些共通的特征与行为。此外，我们没必要专门写一个违例规范，
 * 指出一个方法可能会“掷”出一个 RuntimeException，因为已经假定可能出现那种情况。由于它们用于指出编程中的错误，
 * 所以几乎永远不必专门捕获一个“运行期违例”—— RuntimeException——它在默认情况下会自动得到处理。若必须检查  RuntimeException，
 * 我们的代码就会变得相当繁复。在我们自己的包里，可选择“掷”出一部分 RuntimeException。
 * 如果不捕获这些违例，又会出现什么情况呢？由于编译器并不强制违例规范捕获它们，所以假如不捕获的话，
 * 一个RuntimeException 可能过滤掉我们到达 main()方法的所有途径。为体会此时发生的事情，请试试下面这个例子
 */
public class NeverCaught {

    //RuntimeException 的基础类,不需要try catch 程序会自动处理

    static void f() {
        throw new RuntimeException("From f()");
    }

    static void g() {
        f();
    }

    public static void main(String[] args) {
        g();
    }

    /**
     * 假若一个RuntimeException  获得到达main()的所有途径，同时不被捕获，那么当程序退出时，会为那个违例调用 printStackTrace()。
     * 注意也许能在自己的代码中仅忽略 RuntimeException，因为编译器已正确实行了其他所有控制。因为
     * RuntimeException 在此时代表一个编程错误：
     * (1)	一个我们不能捕获的错误（例如，由客户程序员接收传递给自己方法的一个空句柄）。
     * (2)	作为一名程序员，一个应在自己的代码中检查的错误（如 ArrayIndexOutOfBoundException，此时应注意数组的大小）。
     * 可以看出，最好的做法是在这种情况下违例，因为它们有助于程序的调试。
     * 另外一个有趣的地方是，我们不可将Java 违例划分为单一用途的工具。的确，它们设计用于控制那些讨厌的运行期错误——由代码控制范围之外的其他力量产生。
     * 但是，它也特别有助于调试某些特殊类型的编程错误，那些是编译器侦测不到的。
     */

}
