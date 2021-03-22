package life.majiang.community.test.chapter12.clone;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 9:57
 * @Description: 若需修改一个对象，同时不想改变调用者的对象，就要制作该对象的一个本地副本。这也是本地副本最常见
 * 的一种用途。若决定制作一个本地副本，只需简单地使用 clone()方法即可。Clone 是“克隆”的意思，即制作完全一模一
 * 样的副本。这个方法在基础类Object  中定义成“protected”（受保护）模式。但在希望克隆的任何衍生类中，必须将其覆
 * 盖为“public”模式。例如，标准库类Vector 覆盖了 clone()，所以能为Vector 调用clone()，如下所示：
 *
 * clone()方法产生了一个Object，后者必须立即重新造型为正确类型。这个例子指出 Vector 的clone()方法不能自动尝试克隆
 * Vector 内包含的每个对象——由于别名问题，老的Vector 和克隆的Vector 都包含了相同的对象。我们通常把这种情况叫作
 * “简单复制”或者“浅层复制”，因为它只复制了一个对象的“表面”部    分。实际对象除包含这个“表面”以外，
 * 还包括句柄指向的所有对象，以及那些对象又指向的其他所有对
 * 象，由此类推。这便是“对象网”或“对象关系网”的由来。若能复制下所有这张网，便叫作“全面复制”或者“深层复制”。
 * 在输出中可看到浅层复制的结果，注意对 v2 采取的行动也会影响到 v：
 *
 * v: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 * v: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 *
 * 一般来说，由于不敢保证Vector 里包含的对象是“可以克隆”（注释②）的，所以最好不要试图克隆那些对象。
 *
 * ②：“可以克隆”用英语讲是 cloneable，请留意 Java 库中专门保留了这样的一个关键字。
 */
public class Cloning {
    public static void main(String[] args) {
        Vector v = new Vector();
        for(int i = 0; i < 10; i++ )
            v.addElement(new Int(i));
        System.out.println("v: " + v);
        Vector v2 = (Vector)v.clone();
// Increment all v2's elements:
        for(Enumeration e = v2.elements(); e.hasMoreElements(); )
            ((Int)e.nextElement()).increment();
// See if it changed v's elements:
        System.out.println("v: " + v);
    }

}
