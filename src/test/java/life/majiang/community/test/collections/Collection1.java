package life.majiang.community.test.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author: liu bin bin
 * @Date: 2021/3/16 14:45
 * @Description:   /**  下面是一个collections能使用的所有方法
 *      * boolean add(Object) ＊保证集合内包含了自变量。如果它没有添加自变量，就返回 false（假）
 *      * boolean addAll(Collection) ＊添加自变量内的所有元素。如果没有添加元素，则返回 true（真） void clear() ＊删除集合内的所有元素
 *      * boolean contains(Object) 若集合包含自变量，就返回“真”
 *      * boolean containsAll(Collection) 若集合包含了自变量内的所有元素，就返回“真” boolean isEmpty() 若集合内没有元素，就返回“真”
 *      * Iterator iterator() 返回一个反复器，以用它遍历集合的各元素
 *      * boolean remove(Object)  ＊如自变量在集合里，就删除那个元素的一个实例。如果已进行了删除，就返回“真”
 *      * boolean removeAll(Collection) ＊删除自变量里的所有元素。如果已进行了任何删除，就返回“真”
 *      * boolean retainAll(Collection) ＊只保留包含在一个自变量里的元素（一个理论的“交集”）。如果已进行了任何改变，就返回“真”
 *      * int size() 返回集合内的元素数量
 *      * Object[] toArray() 返回包含了集合内所有元素的一个数组
 *      */
public class Collection1 {
    // Fill with 'size' elements, start

    // counting at 'start':
    public static Collection fill(Collection c, int start, int size) {
        for(int i = start; i < start + size; i++)
            c.add(Integer.toString(i));
        return c;
    }
    // Default to a "start" of 0:
    public static Collection fill(Collection c, int size) {
        return fill(c, 0, size);
    }
    // Default to 10 elements:
    public static Collection fill(Collection c) { return fill(c, 0, 10);
    }
    // Create & upcast to Collection:
    public static Collection newCollection() { return fill(new ArrayList());
        // ArrayList is used for simplicity, but it's
        // only seen as a generic Collection
        // everywhere else in the program.
    }
    // Fill a Collection with a range of values:
    public static Collection
    newCollection(int start, int size) {
        return fill(new ArrayList(), start, size);
    }
    // Moving through a List with an iterator:
    public static void print(Collection c) {
        for(Iterator x = c.iterator(); x.hasNext();) System.out.print(x.next() + " ");
        System.out.println();
    }
    public static void main(String[] args) { Collection c = newCollection(); c.add("ten");
        c.add("eleven"); print(c);
        // Make an array from the List:
        Object[] array = c.toArray();
        // Make a String array from the List:
        String[] str = (String[])c.toArray(new String[1]);
        // Find max and min elements; this means
        // different things depending on the way
        // the Comparable interface is implemented:
        System.out.println("Collections.max(c) = " +
                Collections.max(c)); System.out.println("Collections.min(c) = " +
                Collections.min(c));
        // Add a Collection to another Collection

        c.addAll(newCollection()); print(c);
        c.remove("3"); // Removes the first one print(c);
        c.remove("3"); // Removes the second one
        print(c);
        // Remove all components that are in the
        // argument collection:
        c.removeAll(newCollection());
        print(c);
        c.addAll(newCollection());
        print(c);
        // Is an element in this Collection?
        System.out.println("c.contains(\"4\") = " + c.contains("4"));
        // Is a Collection in this Collection?
        System.out.println("c.containsAll(newCollection()) = " + c.containsAll(newCollection()));
        Collection c2 = newCollection(5, 3);
        // Keep all the elements that are in both
        // c and c2 (an intersection of sets):
        c.retainAll(c2);
        print(c);
        // Throw away all the elements in c that
        // also appear in c2:
        c.removeAll(c2);
        System.out.println("c.isEmpty() = " +
                c.isEmpty());
        c = newCollection(); print(c);
        c.clear(); // Remove all elements
        System.out.println("after c.clear():"); print(c);
    }

}
