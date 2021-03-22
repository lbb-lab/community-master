package life.majiang.community.test.chapter12.onlyread;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 16:52
 * @Description: 和往常一样，Immutable2 包含的方法保留了对象不可变的特征，只要涉及修改，就创建新的对象。完成这些操作的是add()和multiply()方法。
 * 同志类叫作 Mutable，它也含有 add()和multiply()方法。但这些方法能够修改Mutable 对象，而不是新建一个。除此以外，Mutable 的一个方法可用它的数据产生
 * 一个Immutable2 对象，反之亦然。 两个静态方法modify1()和 modify2()揭示出获得同样结果的两种不同方法。在 modify1()中，所有工作都是在Immutable2
 * 类中完成的，我们可看到在进程中创建了四个新的 Immutable2 对象（而且每次重新分配了val，前一个对象就成为垃圾）。
 * 在方法 modify2()中，可看到它的第一个行动是获取 Immutable2 y，然后从中生成一个Mutable（类似于前面对clone()的调用，但这一次创建了一个不同类型的对象）
 * 。随后，用 Mutable 对象进行大量修改操作， 同时用不着新建许多对象。最后，它切换回Immutable2。在这里，我们只创建了两个新对象（Mutable 和
 * Immutable2 的结果），而不是四个。 这一方法特别适合在下述场合应用：
 *
 * (1)	需要不可变的对象，而且
 * (2)	经常需要进行大量修改，或者
 * (3)	创建新的不变对象代价太高
 */
public class Immutable2 {
    private int data;

    public Immutable2(int initVal) {
        data = initVal;
    }

    public int read() { return data; }

    public boolean nonzero() { return data != 0; }

    public Immutable2 add(int x) {
        return new Immutable2(data + x);
    }

    public Immutable2 multiply(int x) {
        return new Immutable2(data * x);
    }

    public Mutable makeMutable() {
        return new Mutable(data);
    }

    public static Immutable2 modify1(Immutable2 y){
        Immutable2 val = y.add(12);
        val = val.multiply(3);
        val = val.add(11);
        val = val.multiply(2);
        return val;
    }

    // This produces the same result:
    public static Immutable2 modify2(Immutable2 y){
        Mutable m = y.makeMutable();
        m.add(12).multiply(3).add(11).multiply(2);
        return m.makeImmutable2();
    }

    public static void main(String[] args) {
        Immutable2 i2 = new Immutable2(47);
        Immutable2 r1 = modify1(i2);
        Immutable2 r2 = modify2(i2);
        System.out.println("i2 = " + i2.read());
        System.out.println("r1 = " + r1.read());
        System.out.println("r2 = " + r2.read());
    }

}
