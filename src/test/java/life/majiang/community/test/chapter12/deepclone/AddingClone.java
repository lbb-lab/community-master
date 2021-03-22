package life.majiang.community.test.chapter12.deepclone;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 11:22
 * @Description: Int3 自Int2 继承而来，并添加了一个新的基本类型成员 int j。大家也许认为自己需要再次覆盖 clone()，
 * 以确保 j 得到复制，但实情并非如此。将Int2 的clone()当作Int3 的clone()调用时，它会调用Object.clone()，
 * 判断出当前操作的是 Int3，并复制Int3 内的所有二进制位。只要没有新增需要克隆的句柄，对 Object.clone()的
 * 一个调用就能完成所有必要的复制——无论clone()是在层次结构多深的一级定义的。
 * 至此，大家可以总结出对Vector 进行深层复制的先决条件：在克隆了Vector 后，必须在其中遍历，并克隆由Vector 指向的每个对象。
 * 为了对Hashtable（散列表）进行深层复制，也必须采取类似的处理。
 * 这个例子剩余的部分显示出克隆已实际进行——证据就是在克隆了对象以后，可以自由改变它，而原来那个 对象不受任何影响。
 */
public class AddingClone {
    public static void main(String[] args) {
        Int2 x = new Int2(10);
        Int2 x2 = (Int2)x.clone();
        x2.increment();
        System.out.println("x = " + x + ", x2 = " + x2);
// Anything inherited is also cloneable:
        Int3 x3 = new Int3(7);
        x3 = (Int3)x3.clone();

        Vector v = new Vector();
        for(int i = 0; i < 10; i++ )
            v.addElement(new Int2(i));
        System.out.println("v: " + v);
        Vector v2 = (Vector)v.clone();
// Now clone each element:
        for(int i = 0; i < v.size(); i++)
            v2.setElementAt(((Int2)v2.elementAt(i)).clone(), i);
// Increment all v2's elements:
        for(Enumeration e = v2.elements(); e.hasMoreElements(); )
            ((Int2)e.nextElement()).increment();
// See if it changed v's elements:
        System.out.println("v: " + v);
        System.out.println("v2: " + v2);
    }

}
