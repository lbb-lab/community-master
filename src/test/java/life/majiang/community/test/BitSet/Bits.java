package life.majiang.community.test.BitSet;

import java.util.BitSet;
import java.util.Random;

/**
 * @author: liu bin bin
 * @Date: 2021/3/15 12:41
 * @Description: BitSet 实际是由“二进制位”构成的一个 Vector。如果希望高效率地保存大量“开－关”信息，就应使用BitSet。
 * 它只有从尺寸的角度看才有意义；如果希望的高效率的访问，那么它的速度会比使用一些固有类型 的数组慢一些。
 * 此外，BitSet 的最小长度是一个长整数（Long）的长度：64 位。这意味着假如我们准备保存比这更小的数
 * 据，如 8 位数据，那么 BitSet 就显得浪费了。所以最好创建自己的类，用它容纳自己的标志位。
 * 在一个普通的Vector 中，随我们加入越来越多的元素，集合也会自我膨胀。在某种程度上，BitSet 也不例外。也就是说，它有时会自行扩展，
 * 有时则不然。而且Java 的1.0 版本似乎在这方面做得最糟，它的BitSet 表现十分差强人意（Java1.1 已改正了这个问题）。
 * 下面这个例子展示了BitSet 是如何运作的，同时演示了 1.0 版本的错误
 */
public class Bits {
    public static void main(String[] args) {
        Random rand = new Random();
        // Take the LSB of nextInt():
        byte bt = (byte)rand.nextInt();    //byte一个字节，8位  (10进制)101 ---》 (2进制)01100101
        BitSet bb = new BitSet();
        for(int i = 7; i >=0; i--)         //                   (10进制)128  ---》(2进制)10000000
            if(((1 << i) &	bt) != 0) bb.set(i); //这里是1左移i位 ，1左移7位是 (2进制)10000000 -> (10进制)128
            else
                bb.clear(i);
        System.out.println("byte value: " + bt);
        printBitSet(bb);

        short st = (short)rand.nextInt();
        BitSet bs = new BitSet();
        for(int i = 15; i >=0; i--)
            if(((1 << i) &	st) != 0)
            bs.set(i);
        else
            bs.clear(i);
        System.out.println("short value: " + st);
        printBitSet(bs);

        int it = rand.nextInt(); BitSet bi = new BitSet();
        for(int i = 31; i >=0; i--)
            if(((1 << i) &	it) != 0) bi.set(i);
            else
                bi.clear(i); System.out.println("int value: " + it); printBitSet(bi);

        // Test bitsets >= 64 bits:
        BitSet b127 = new BitSet(); b127.set(127);

        System.out.println("set bit 127: " + b127);
        BitSet b255 = new BitSet(65);
        b255.set(255);
        System.out.println("set bit 255: " + b255);
        BitSet b1023 = new BitSet(512);
        // Without the following, an exception is thrown
        // in the Java 1.0 implementation of BitSet:
        b1023.set(1023);
        b1023.set(1024);
        System.out.println("set bit 1023: " + b1023);
    }
    static void printBitSet(BitSet b) {
        System.out.println("bits: " + b);
        String bbits = new String();
        for(int j = 0; j < b.size() ; j++)
            bbits += (b.get(j) ? "1" : "0");
        System.out.println("bit pattern: " + bbits);
    }

//    随机数字生成器用于创建一个随机的byte、short 和int。每一个都会转换成BitSet 内相应的位模型。此时一切都很正常，因为BitSet 是64 位的，
//    所以它们都不会造成最终尺寸的增大。但在 Java 1.0 中，一旦BitSet 大于64 位，就会出现一些令人迷惑不解的行为。假如我们设置一个只比BitSet
//    当前分配存储空间大出1   的一个位，它能够正常地扩展。但一旦试图在更高的位置设置位，同时不先接触边界，就会得到一个恼人的违例。
//    这正是由于 BitSet 在Java 1.0 里不能正确扩展造成的。本例创建了一个 512 位的BitSet。构建器分配的存储空间是位数的两倍。所以假如设置位 1024 或
//    更高的位，同时没有先设置位1023，就会在Java1.0 里得到一个违例。但幸运的是，这个问题已在 Java 1.1 得到了改正。所以如果是为Java 1.0 写代码，
//    请尽量避免使用BitSet

}
