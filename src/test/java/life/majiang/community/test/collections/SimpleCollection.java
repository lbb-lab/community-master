package life.majiang.community.test.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author: liu bin bin
 * @Date: 2021/3/16 14:40
 * @Description: 利用iterator()方法，所有集合都能生成一个“反复器”（Iterator）。反复器其实就象一个“枚举”
 * （Enumeration），是后者的一个替代物，只是：
 * (1)	它采用了一个历史上默认、而且早在OOP 中得到广泛采纳的名字（反复器）。
 * (2)	采用了比Enumeration 更短的名字：hasNext()代替了 hasMoreElement()，而next()代替了nextElement()。
 * (3)	添加了一个名为remove() 的新方法，可删除由Iterator 生成的上一个元素。所以每次调用 next()的时
 * 候，只需调用remove() 一次。
 * 在SimpleCollection.java 中，大家可看到创建了一个反复器，并用它在集合里遍历，打印出每个元素。
 */
public class SimpleCollection {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        for(int i = 0; i < 10; i++)
        c.add(Integer.toString(i));
        Iterator it = c.iterator();
        while(it.hasNext())
        System.out.println(it.next());
    }


}
