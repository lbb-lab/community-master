package life.majiang.community.test.chapter12.onlyread;

import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 16:29
 * @Description:
 *  *     尽管在一些特定的场合，由clone()产生的本地副本能够获得我们希望的结果，但程序员（方法的作者）不 得不亲自禁止别名处理的副作用。
 *  * 假如想制作一个库，令其具有常规用途，但却不能担保它肯定能在正确的  类中得以克隆，这时又该怎么办呢？更有可能的一种情况是，
 *  * 假如我们想让别名发挥积极的作用——禁止不必要的对象复制——但却不希望看到由此造成的副作用，那么又该如何处理呢？
 *  * 一个办法是创建“不变对象”，令其从属于只读类。可定义一个特殊的类，使其中没有任何方法能造成对象
 *  * 内部状态的改变。在这样的一个类中，别名处理是没有问题的。因为我们只能读取内部状态，所以当多处代码都读取相同的对象时，不会出现任何副作用。
 *  * 作为“不变对象”一个简单例子，Java 的标准库包含了“封装器”（wrapper）类，可用于所有基本数据类型。大家可能已发现了这一点，
 *  * 如果想在一个象Vector（只采用Object 句柄）这样的集合里保存一个 int 数值，可以将这个 int 封装到标准库的Integer 类内部。如下所示：
 */
public class MutableInteger {
    public static void main(String[] args) {
        Vector v = new Vector();
        for(int i = 0; i < 10; i++)
            v.addElement(new IntValue(i));
        System.out.println(v);
        for(int i = 0; i < v.size(); i++)
            ((IntValue)v.elementAt(i)).n++;
        System.out.println(v);
    }

}
