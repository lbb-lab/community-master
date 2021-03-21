package life.majiang.community.test.ioserializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 18:21
 * @Description: 未恢复 Blip2 对象的原因是那样做会导致一个违例。你找出了 Blip1 和Blip2 之间的区别吗？
 * Blip1 的构建器是“公共的”（public），Blip2 的构建器则不然，这样便会在恢复时造成违例。
 * 试试将 Blip2 的构建器属性变成“public”，然后删除//!注释标记，看看是否能得到正确的结果。
 * 恢复b1 后，会调用 Blip1 默认构建器。这与恢复一个Serializable（可序列化）对象不同。在后者的情况下，
 * 对象完全以它保存下来的二进制位为基础恢复，不存在构建器调用。而对一个Externalizable 对象，
 * 所有普通的默认构建行为都会发生（包括在字段定义时的初始化），而且会调用readExternal()。必须注意这  一事实——
 * 特别注意所有默认的构建行为都会进行——否则很难在自己的 Externalizable 对象中产生正确的行为。
 */
public class Blips {
    public static void main(String[] args) {
        System.out.println("Constructing objects:");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blips.out"));
            System.out.println("Saving objects:");
            o.writeObject(b1);
            o.writeObject(b2);
            o.close();
            // Now get them back:
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"));
            System.out.println("Recovering b1:");
            b1 = (Blip1)in.readObject();
            // OOPS! Throws an exception:
            System.out.println("Recovering b2:");
            b2 = (Blip2)in.readObject();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
} ///:


