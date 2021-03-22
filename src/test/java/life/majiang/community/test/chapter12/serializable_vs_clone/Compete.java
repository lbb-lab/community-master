package life.majiang.community.test.chapter12.serializable_vs_clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 11:30
 * @Description: 其中，Thing2 和Thing4 包含了成员对象，所以需要进行一些深层复制。一个有趣的地方是尽管Serializable 类很容易设置，
 * 但在复制它们时却要做多得多的工作。克隆涉及到大量的类设置工作，但实际的对象复制是相当简单的。结果很好地说明了一切。
 * 下面是几次运行分别得到的结果：
 * 的确
 * Duplication via serialization: 3400 Milliseconds
 * Duplication via cloning: 110 Milliseconds
 * Duplication via serialization: 3410 Milliseconds
 * Duplication via cloning: 110 Milliseconds
 * Duplication via serialization: 3520 Milliseconds
 * Duplication via cloning: 110 Milliseconds
 *
 * 除了序列化和克隆之间巨大的时间差异以外，我们也注意到序列化技术的运行结果并不稳定，而克隆每一次 花费的时间都是相同的。
 */
public class Compete {
    static final int SIZE = 5000;
    public static void main(String[] args) {
        Thing2[] a = new Thing2[SIZE];
        for(int i = 0; i < a.length; i++)
            a[i] = new Thing2();
        Thing4[] b = new Thing4[SIZE];
        for(int i = 0; i < b.length; i++)
            b[i] = new Thing4();
        try {
            long t1 = System.currentTimeMillis();
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(buf);
            for(int i = 0; i < a.length; i++)
                o.writeObject(a[i]);
// Now get copies:
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream( buf.toByteArray()));
            Thing2[] c = new Thing2[SIZE];
            for(int i = 0; i < c.length; i++)
                c[i] = (Thing2)in.readObject();
            long t2 = System.currentTimeMillis();
            System.out.println("Duplication via serialization: " + (t2 - t1) + " Milliseconds");
// Now try cloning:
            t1 = System.currentTimeMillis();
            Thing4[] d = new Thing4[SIZE];
            for(int i = 0; i < d.length; i++)
                d[i] = (Thing4)b[i].clone();
            t2 = System.currentTimeMillis();
            System.out.println("Duplication via cloning: " + (t2 - t1) + " Milliseconds");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
