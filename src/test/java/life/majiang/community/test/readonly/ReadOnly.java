package life.majiang.community.test.readonly;

import life.majiang.community.test.collections.Collection1;
import life.majiang.community.test.map.Map1;

import java.util.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 10:11
 * @Description:    使Collection 或Map 不可修改
 * 通常，创建 Collection 或Map 的一个“只读”版本显得更有利一些。Collections 类允许我们达到这个目标，方法是将原始容器传递进入一个方法，
 * 并令其传回一个只读版本。这个方法共有四种变化形式，分别用  于Collection（如果不想把集合当作一种更特殊的类型对待）、List、Set 以及Map。
 * 下面这个例子演示了为它们分别构建只读版本的正确方法
 */
public class ReadOnly {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        Collection1.fill(c); // Insert useful data
        c = Collections.unmodifiableCollection(c);
        Collection1.print(c); // Reading is OK
        //!c.add("one"); // Can't change it

        List a = new ArrayList();
        Collection1.fill(a);
        a = Collections.unmodifiableList(a);
        ListIterator lit = a.listIterator();
        System.out.println(lit.next()); // Reading OK
        //!lit.add("one"); // Can't change it

        Set s = new HashSet();
        Collection1.fill(s);
        s = Collections.unmodifiableSet(s);
        Collection1.print(s); // Reading OK
        //!s.add("one"); // Can't change it

        Map m = new HashMap();
        Map1.fill(m, Map1.testData1);
        m = Collections.unmodifiableMap(m);
        Map1.print(m); // Reading OK
        //! m.put("Ralph", "Howdy!");
    }

}
