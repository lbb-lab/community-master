package life.majiang.community.test.ioserializable.transient1;

import java.io.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 19:10
 * @Description: 在这个例子中，一个String 保持原始状态，其他设为transient（临时），以便证明非临时字段会被defaultWriteObject()方法自动保存，
 * 而 transient 字段必须在程序中明确保存和恢复。字段是在构建器内部初始化的，而不是在定义的时候，这证明了它们不会在重新装配的时候被某些自动化机制初始化。
 * 若准备通过默认机制写入对象的非 transient 部分，那么必须调用defaultWriteObject()，令其作为
 *
 * writeObject() 中的第一个操作；并调用defaultReadObject()，令其作为 readObject()的第一个操作。这些都是不常见的调用方法。举个例子来说，
 * 当我们为一个ObjectOutputStream  调用defaultWriteObject()的时候，而且没有为其传递参数，就需要采取这种操作，
 * 使其知道对象的句柄以及如何写入所有非transient   的部分。这种做法非常不便。
 * transient 对象的存储与恢复采用了我们更熟悉的代码。现在考虑一下会发生一些什么事情。在 main()中会
 * 创建一个SerialCtl 对象，随后会序列化到一个ObjectOutputStream 里（注意这种情况下使用的是一个缓冲区，
 * 而非文件——与ObjectOutputStream 完全一致）。正式的序列化操作是在下面这行代码里发生的： o.writeObject(sc);
 * 其中，writeObject() 方法必须核查sc，判断它是否有自己的 writeObject()方法（不是检查它的接口——它
 * 根本就没有，也不是检查类的类型，而是利用反射方法实际搜索方法）。若答案是肯定的，就使用那个方  法。
 * 类似的情况也会在 readObject()上发生。或许这是解决问题唯一实际的方法，但确实显得有些古怪。
 */
public class SerialCtl implements Serializable {
    String a;

    transient String b;

    public SerialCtl(String aa, String bb) {
        a = "Not Transient: " + aa;
        b = "Transient: " + bb;
    }

    public String toString() { return a + "\n" + b;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(b);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        b = (String)stream.readObject();
    }

    public static void main(String[] args) {
        SerialCtl sc = new SerialCtl("Test1", "Test2");
        System.out.println("Before:\n" + sc);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        try {
            ObjectOutputStream o = new ObjectOutputStream(buf);
            o.writeObject(sc);
// Now get it back:
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream( buf.toByteArray()));
            SerialCtl sc2 = (SerialCtl)in.readObject();
            System.out.println("After:\n" + sc2);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
