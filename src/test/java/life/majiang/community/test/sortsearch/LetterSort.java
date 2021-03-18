package life.majiang.community.test.sortsearch;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: liu bin bin
 * @Date: 2021/3/17 17:37
 * @Description:  Array并不能将大小写一样的字母进行排序，这里实现字母真正的大小写排序
 */
public class LetterSort implements Comparator {
    public int compare(Object o1, Object o2) {
    // Assume it's used only for Strings...
        String s1 = ((String)o1).toLowerCase();
        String s2 = ((String)o2).toLowerCase();
        return s1.compareTo(s2);
    }

    public static void main(String[] args) {
        String[] s = Array1.randStrings(4, 10);
        Array1.print(s);
        LetterSort ac = new LetterSort();
        Arrays.sort(s, ac);
        Array1.print(s);
// Must use the Comparator to search, also:
        int loc = Arrays.binarySearch(s, s[3], ac);
        System.out.println("Location of " + s[3] + " = " + loc);
    }

}
