package life.majiang.community.test.sortsearch;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: liu bin bin
 * @Date: 2021/3/17 16:36
 * @Description: 类的第一部分包含了用于产生随机字串对象的实用工具，可供选择的随机字母保存在一个字符数组中。randString()返回一个任意长度的字串；
 * 而 readStrings() 创建随机字串的一个数组，同时给定每个字串的长度以及希望的数组大小。两个print()方法简化了对示范数组的显示。
 * 在 main()中，Random.nextBytes() 用随机选择的字节填充数组自变量（没有对应的Random 方法用于创建其他基本数据类型的数组）。获得一个数组后，
 * 便可发现为了执行sort()或者 binarySearch()，只需发出一次方法调用即可。与 binarySearch() 有关的还有一个重要的警告：
 * 若在执行一次binarySearch()之前不调用 sort()，便会发生不可预测的行为， 其中甚至包括无限循环。
 * 对String  的排序以及搜索是相似的，但在运行程序的时候，我们会注意到一个有趣的现象：排序遵守的是字典顺序，亦即大写字母在字符集中位于小写字母的前面。
 * 因此，所有大写字母都位于列表的最前面，后面再   跟上小写字母——Z 居然位于a 的前面。似乎连电话簿也是这样排序的。
 */
public class Array1 {
    static Random r = new Random();
    static String ssource = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";
    static char[] src = ssource.toCharArray();
    // Create a random String
    public static String randString(int length) {
        char[] buf = new char[length];
        int rnd;
        for(int i = 0; i < length; i++) {
            rnd = Math.abs(r.nextInt()) % src.length;
            buf[i] = src[rnd];
        }
        return new String(buf);
    }

    // Create a random array of Strings: public static
    static String[] randStrings(int length, int size) {
        String[] s = new String[size];
        for(int i = 0; i < size; i++)
            s[i] = randString(length);
        return s;
    }

    public static void print(byte[] b) {
        for(int i = 0; i < b.length; i++)
            System.out.print(b[i] + " ");
        System.out.println();
    }

    public static void print(String[] s) {
        for(int i = 0; i < s.length; i++)
            System.out.print(s[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        byte[] b = new byte[15];
        r.nextBytes(b); // Fill with random bytes
        print(b);
        Arrays.sort(b);
        print(b);
        int loc = Arrays.binarySearch(b, b[10]);   //在执行一次 binarySearch()——即二进制搜索——之前，必须对一个 List 或者一个数组执行sort()）。
        System.out.println("Location of " + b[10] + " = " + loc);
        // Test String sort & search:
        String[] s = randStrings(4, 10);
        print(s);
        Arrays.sort(s);
        print(s);
        loc = Arrays.binarySearch(s, s[4]);       //binarySearch是二分搜索的意思
        System.out.println("Location of " + s[4] + " = " + loc);
    }

}
