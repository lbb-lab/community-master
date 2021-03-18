package life.majiang.community.test.sortsearch;

import life.majiang.community.test.collections.Collection1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 9:31
 * @Description:  可用与数组相同的形式排序和搜索一个列表（List）。用于排序和搜索列表的静态方法包含在类Collections 中，
 * 但它们拥有与 Arrays 中差不多的签名：sort(List)用于对一个实现了 Comparable 的对象列表进行排序；
 * binarySearch(List,Object)用于查找列表中的某个对象；sort(List,Comparator)利用一个  “比较器”对一个列表进行排序；
 * 而binarySearch(List,Object,Comparator)则用于查找那个列表中的一个对象，
 * TreeMap 也必须根据 Comparable 或者Comparator 对自己的对象进行排序。
 */
public class ListSort {
    public static void main(String[] args) {
        final int SZ = 20;
// Using "natural comparison method":
        List a = new ArrayList();
        for(int i = 0; i < SZ; i++)
            a.add(new CompClass( (int)(Math.random() *100)));
        Collection1.print(a);
        Collections.sort(a);
        Collection1.print(a);
        Object find = a.get(SZ/2);

        int loc = Collections.binarySearch(a, find);
        System.out.println("Location of " + find + " = " + loc);
// Using a Comparator:
        List b = new ArrayList();
        for(int i = 0; i < SZ; i++)
            b.add(Array1.randString(4));
        Collection1.print(b);
        LetterSort ac = new LetterSort();
        Collections.sort(b, ac);
        Collection1.print(b);
        find = b.get(SZ/2);
// Must use the Comparator to search, also:
        loc = Collections.binarySearch(b, find, ac);
        System.out.println("Location of " + find + " = " + loc);
    }

}
