package life.majiang.community.test.chapter12.clone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 10:10
 * @Description: 不管怎样，clone()必须能够访问，所以必须将其设为 public（公共的）。其次，作为clone()的初期行动，
 * 应调用 clone()的基础类版本。这里调用的clone()是 Object 内部预先定义好的。之所以能调用它，是由于它具有
 * protected（受到保护的）属性，所以能在衍生的类里访问。
 * Object.clone()会检查原先的对象有多大，再为新对象腾出足够多的内存，将所有二进制位从原来的对象复
 *
 * 制到新对象。这叫作“按位复制”，而且按一般的想法，这个工作应该是由 clone()方法来做的。但在Object.clone()
 * 正式开始操作前，首先会检查一个类是否  Cloneable，即是否具有克隆能力——换言之，它是否实现了 Cloneable 接口。
 * 若未实现，Object.clone()就掷出一个 CloneNotSupportedException 违例，指出我们不能克隆它。因此，我们最好用一个
 * try-catch 块将对 super.clone() 的调用代码包围（或封装）起来，试图捕获一个应当永不出现的违例（因为这里确实已实现了Cloneable 接口）。
 * 在LocalCopy 中，两个方法 g()和f()揭示出两种参数传递方法间的差异。其中，g()演示的是按引用传递， 它会修改外部对象，
 * 并返回对那个外部对象的一个引用。而f()是对自变量进行克隆，所以将其分离出来，   并让原来的对象保持独立。随后，
 * 它继续做它希望的事情。甚至能返回指向这个新对象的一个句柄，而且不   会对原来的对象产生任何副作用。注意下面这个多少有些古怪的语句：
 * v = (MyObject)v.clone();
 * 它的作用正是创建一个本地副本。为避免被这样的一个语句搞混淆，记住这种相当奇怪的编码形式在Java 中是完全允许的，
 * 因为有一个名字的所有东西实际都是一个句柄。所以句柄 v 用于克隆一个它所指向的副本， 而且最终返回指向基础类型Object
 * 的一个句柄（因为它在 Object.clone()中是那样被定义的），随后必须将其造型为正确的类型。
 */
public class LocalCopy {
    static MyObject g(MyObject v) {
// Passing a handle, modifies outside object:
        v.i++;
        return v;
    }

    static MyObject f(MyObject v) {
        v = (MyObject)v.clone(); // Local copy
        v.i++;
        return v;
    }

    public static void main(String[] args) {
        MyObject a = new MyObject(11);
        MyObject b = g(a);
// Testing handle equivalence,
// not object equivalence:
        if(a == b)
            System.out.println("a == b");
        else
            System.out.println("a != b");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        MyObject c = new MyObject(47);
        MyObject d = f(c);
        if(c == d)
            System.out.println("c == d");
        else
            System.out.println("c != d");
        System.out.println("c = " + c);
        System.out.println("d = " + d);
    }

}
