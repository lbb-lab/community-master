package life.majiang.community.test.enumerationtest;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/15 10:24
 * @Description:            java 枚举类 Enumeration
 *                         （1）用一个名为element()的方法要求集合为我们提供一个Enumeration。我们首次调用他的nextElement时，返回的是第一个元素
 *                         （2）用nextElement获得下一个对象
 *                         （3）用hasMoreElements（）检查序列中是否还有更多的对象
 *
 *                         使用枚举类，不必要关心里面的元素的数量
 */
public class CatsAndDog2 {
    public static void main(String[] args){
        Vector cats = new Vector();
        for (int i=0;i<7;i++){
            cats.addElement(new Cat2(i));
        }
        cats.addElement(new Dog2(7));
        Enumeration e = cats.elements();
        while (e.hasMoreElements()){
            ((Cat2)e.nextElement()).print();     //这里要把强转的类全部用括号括起来，最后一个是dog类型，编译通过，运行会报错
        }
    }

}
