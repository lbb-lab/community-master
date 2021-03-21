package life.majiang.community.test.ioserializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 19:23
 * @Description: 这里一件有趣的事情是也许是能针对一个字节数组应用对象的序列化，从而实现对任何Serializable（可序  列化）
 * 对象的一个“全面复制”（全面复制意味着复制的是整个对象网，而不仅是基本对象和它的句柄）。   复制问题将在第12 章进行全面讲述。
 * Animal 对象包含了类型为 House 的字段。在 main()中，会创建这些Animal 的一个 Vector，并对其序列化两
 * 次，分别送入两个不同的数据流内。这些数据重新装配并打印出来后，可看到下面这样的结果（对象在每次 运行时都会处在不同的内存位置，
 * 所以每次运行的结果有区别）
 */
public class MyWorld {
    public static void main(String[] args) {
        House house = new House();
        Vector	animals = new Vector();
        animals.addElement(new Animal("Bosco the dog", house));
        animals.addElement(new Animal("Ralph the hamster", house));
        animals.addElement(new Animal("Fronk the cat", house));
        System.out.println("animals: " + animals);

        try {
            ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
            ObjectOutputStream o1 = new ObjectOutputStream(buf1);
            o1.writeObject(animals);
            o1.writeObject(animals); // Write a 2nd set
// Write to a different stream:
            ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
            ObjectOutputStream o2 = new ObjectOutputStream(buf2);
            o2.writeObject(animals);
// Now get them back:
            ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream( buf1.toByteArray()));
            ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream( buf2.toByteArray()));
            Vector animals1 = (Vector)in1.readObject();
            Vector animals2 = (Vector)in1.readObject();
            Vector animals3 = (Vector)in2.readObject();
            System.out.println("animals1: " + animals1);
            System.out.println("animals2: " + animals2);
            System.out.println("animals3: " + animals3);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
