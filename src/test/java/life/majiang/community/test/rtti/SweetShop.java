package life.majiang.community.test.rtti;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 23:56
 * @Description: 为理解 RTTI 在Java 里如何工作，首先必须了解类型信息在运行期是如何表示的。这时要用到一个名为“Class 对象”
 * 的特殊形式的对象，其中包含了与类有关的信息（有时也把它叫作“元类”）。事实上，我们要用 Class 对象创建属于某个类的全部“常规”或“普通”对象。
 * 对于作为程序一部分的每个类，它们都有一个 Class 对象。换言之，每次写一个新类时，同时也会创建一个
 *
 * Class 对象（更恰当地说，是保存在一个完全同名的.class 文件中）。在运行期，一旦我们想生成那个类的一个对象，用于执行程序的 Java
 * 虚拟机（JVM）首先就会检查那个类型的Class 对象是否已经载入。若尚未载入，JVM 就会查找同名的.class 文件，并将其载入。所以Java
 * 程序启动时并不是完全载入的，这一点与许多传统语言都不同。
 * 一旦那个类型的Class 对象进入内存，就用它创建那一类型的所有对象。
 * 若这种说法多少让你产生了一点儿迷惑，或者并没有真正理解它，下面这个示范程序或许能提供进一步的帮 助：
 *
 *
 * 对每个类来说（Candy，Gum 和Cookie），它们都有一个 static 从句，用于在类首次载入时执行。相应的信息会打印出来，
 * 告诉我们载入是什么时候进行的。在main()中，对象的创建代码位于打印语句之间，以便侦  测载入时间。
 * 特别有趣的一行是：
 * Class.forName("Gum");
 * 该方法是Class（即全部Class 所从属的）的一个 static 成员。而 Class 对象和其他任何对象都是类似的，
 *
 * 所以能够获取和控制它的一个句柄（装载模块就是干这件事的）。为获得 Class 的一个句柄，一个办法是使用forName()。
 * 它的作用是取得包含了目标类文本名字的一个  String（注意拼写和大小写）。最后返回的是一个Class 句柄。
 *
 * 该程序在某个JVM 中的输出如下：
 *
 * inside main Loading Candy
 * After creating Candy Loading Gum
 * After Class.forName("Gum") Loading Cookie
 * After creating Cookie
 *
 * 可以看到，每个Class 只有在它需要的时候才会载入，而 static 初始化工作是在类载入时执行的。非常有趣的是，另一个 JVM 的输出变成了另一个样子：
 *
 * Loading Candy Loading Cookie inside main
 * After creating Candy Loading Gum
 * After Class.forName("Gum")
 * After creating Cookie
 *
 * 看来JVM 通过检查main()中的代码，已经预测到了对Candy 和Cookie 的需要，但却看不到Gum，因为它是通过对forName()的一个调用创建的，而
 * 不是通过更典型的 new 调用。尽管这个JVM 也达到了我们希望的效
 * 果，因为确实会在我们需要之前载入那些类，但却不能肯定这儿展示的行为百分之百正确。
 *
 * 1. 类标记
 * 在Java 1.1 中，可以采用第二种方式来产生 Class 对象的句柄：使用“类标记”。对上述程序来说，看起来就象下面这样：
 * Gum.class;
 * 这样做不仅更加简单，而且更安全，因为它会在编译期间得到检查。由于它取消了对方法调用的需要，所以 执行的效率也会更高。
 * 类标记不仅可以应用于普通类，也可以应用于接口、数组以及基本数据类型。除此以外，针对每种基本数据
 * 类型的封装器类，它还存在一个名为TYPE 的标准字段。TYPE 字段的作用是为相关的基本数据类型产生 Class 对象的一个句柄，如下所示：
 *
 * ... is equivalent to ...
 * boolean.class	Boolean.TYPE
 * char.class	Character.TYPE
 * byte.class	Byte.TYPE
 * short.class	Short.TYPE
 * int.class	Integer.TYPE
 * long.class	Long.TYPE
 * float.class	Float.TYPE
 * double.class	Double.TYPE
 * void.class	Void.TYPE
 *
 */
public class SweetShop {
    public static void main(String[] args) {
        System.out.println("inside main");
        new Candy();
        System.out.println("After creating Candy");
        try {
            Class.forName("Gum");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("After Class.forName(\"Gum\")");
        new Cookie();
        System.out.println("After creating Cookie");
    }

}
