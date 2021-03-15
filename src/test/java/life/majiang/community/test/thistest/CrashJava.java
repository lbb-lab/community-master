package life.majiang.community.test.thistest;

import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/15 10:55
 * @Description:          若只是简单的创建一个CrashJava对象，并将其打印出来，就会得到无穷无尽的一系列违例错误。然而
 *                        假如将CrashJava置于一个vector，并像这里演示的那样打印vector就不会出现什么错误提示，
 *                        甚至连一个违例都不会出现，此时java只是简单的崩溃
 *
 *                        此时发生的时字串的自动类型的转换。当我们使用下述语句时：
 *                        "CrashJava address：" + this 编译器就在一个字串后面发现了一个“+”以及好像并非字串的其他的东西，所以试图将this
 *                        转换成一个字串。转换时调用toString(),后者会产生一个递归调用。若在一个vector内出现这种事情，看起来堆栈就会溢出
 *                        同时违例控制机制根本没有机会做出响应
 *
 *                        若确实想在这种情况下打印对象的地址，解决方案就是调用Object的toString方法。此时就不必加入this，只需使用super.toString
 *                        （每个类的根类都是object）。
 *                        当然，采取这种做法也有一个前提：我们必须从object直接继承，或者没有一个父类覆盖了toString方法
 */
public class CrashJava {
    public String toString(){
//        return "CrashJava address: " + this + "\n";
        return super.toString();
    }

    public static void main(String[] args){
        Vector vector = new Vector();
        for (int i =0;i<10;i++){
            vector.addElement(new CrashJava());
        }
        System.out.println(vector);
    }

}
