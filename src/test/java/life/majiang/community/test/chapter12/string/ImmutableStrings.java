package life.majiang.community.test.chapter12.string;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 17:25
 * @Description:
 * 1.    隐式常数
 * 若使用下述语句： String s = "asdf";
 * String x = Stringer.upcase(s);
 * 那么真的希望upcase() 方法改变自变量或者参数吗？我们通常是不愿意的，因为作为提供给方法的一种信息，自变量一般是拿给代码的读者看的，
 * 而不是让他们修改。这是一个相当重要的保证，因为它使代码更易 编写和理解。
 * 为了在 C++中实现这一保证，需要一个特殊关键字的帮助：const。利用这个关键字，程序员可以保证一个句柄（C++叫“指针”或者“引用”）
 * 不会被用来修改原始的对象。但这样一来，C++程序员需要用心记住在所  有地方都使用const。这显然易使人混淆，也不容易记住。
 *
 * 2.	覆盖"+"和StringBuffer
 * 利用前面提到的技术，String 类的对象被设计成“不可变”。若查阅联机文档中关于String 类的内容（本章稍后还要总结它），就会发现类中能够修改 String
 * 的每个方法实际都创建和返回了一个崭新的String 对象，新对象里包含了修改过的信息——原来的 String 是原封未动的。因此，Java 里没有与C++的const
 * 对应的特性可用来让编译器支持对象的不可变能力。若想获得这一能力，可以自行设置，就象String 那样。
 * 由于String 对象是不可变的，所以能够根据情况对一个特定的 String 进行多次别名处理。因为它是只读的，所以一个句柄不可能会改变一些会影响其他句柄的东西。
 * 因此，只读对象可以很好地解决别名问题。
 * 通过修改产生对象的一个崭新版本，似乎可以解决修改对象时的所有问题，就象 String 那样。但对某些操作来讲，这种方法的效率并不高。
 * 一个典型的例子便是为String  对象覆盖的运算符“+”。“覆盖”意味着在与一个特定的类使用时，它的含义已发生了变化（用于String 的
 * “+”和“+=”是Java 中能被覆盖的唯一运算符，Java 不允许程序员覆盖其他任何运算符——注释④）。
 *
 * ④：C++允许程序员随意覆盖运算符。由于这通常是一个复杂的过程（参见《Thinking in C++》，Prentice- Hall 于1995 年出版），所以Java 的设计者认定
 * 它是一种“糟糕”的特性，决定不在 Java 中采用。但具有讽剌意味的是，运算符的覆盖在Java 中要比在C++中容易得多。
 *
 * 针对String 对象使用时，“+”允许我们将不同的字串连接起来： String s = "abc" + foo + "def" + Integer.toString(47);
 * 可以想象出它“可能”是如何工作的：字串"abc"可以有一个方法append()，它新建了一个字串，其中包含
 * "abc"以及foo 的内容；这个新字串然后再创建另一个新字串，在其中添加"def"；以此类推。
 * 这一设想是行得通的，但它要求创建大量字串对象。尽管最终的目的只是获得包含了所有内容的一个新字  串，但中间却要用到大量字串对象，而且要不断地进行
 * 垃圾收集。我怀疑 Java 的设计者是否先试过种方法（这是软件开发的一个教训——除非自己试试代码，并让某些东西运行起来，否则不可能真正了解系统）。
 * 我还怀疑他们是否早就发现这样做获得的性能是不能接受的。
 * 解决的方法是象前面介绍的那样制作一个可变的同志类。对字串来说，这个同志类叫作StringBuffer，编译  器可以自动创建一个StringBuffer，
 * 以便计算特定的表达式，特别是面向String 对象应用覆盖过的运算符+ 和+=时。下面这个例子可以解决这个问题：
 */
public class ImmutableStrings {
    public static void main(String[] args) {
        String foo = "foo";
        String s = "abc" + foo + "def" + Integer.toString(47);
        System.out.println(s);

        // The "equivalent" using StringBuffer:
        StringBuffer sb = new StringBuffer("abc"); // Creates String!
        sb.append(foo);
        sb.append("def"); // Creates String!
        sb.append(Integer.toString(47));
        System.out.println(sb);
    }

    /**
     * 创建字串s 时，编译器做的工作大致等价于后面使用 sb 的代码——创建一个StringBuffer，并用 append() 将新字符直接加入 StringBuffer 对象
     * （而不是每次都产生新对象）。尽管这样做更有效，但不值得每次都创建象"abc"和"def"这样的引号字串，编译器会把它们都转换成 String 对象。
     * 所以尽管StringBuffer 提供了更高的效率，但会产生比我们希望的多得多的对象。
     */
}
