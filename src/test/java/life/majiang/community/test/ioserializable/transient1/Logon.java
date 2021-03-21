package life.majiang.community.test.ioserializable.transient1;

import java.io.*;
import java.util.Date;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 18:50
 * @Description: 1.    transient（临时）关键字
 * 控制序列化过程时，可能有一个特定的子对象不愿让Java 的序列化机制自动保存与恢复。一般地，若那个子对象包含了不想序列化的敏感信息（如密码），
 * 就会面临这种情况。即使那种信息在对象中具有“private”（私有）属性，但一旦经序列化处理，人们就可以通过读取一个文件，或者拦截网络传输得到它。
 * 为防止对象的敏感部分被序列化，一个办法是将自己的类实现为Externalizable，就象前面展示的那样。这 样一来，没有任何东西可以自动序列化，
 * 只能在writeExternal()明确序列化那些需要的部分。然而，若操作的是一个 Serializable 对象，所有序列化操作都会自动进行。
 * 为解决这个问题，可以用transient（临时）逐个字段地关闭序列化，它的意思是“不要麻烦你（指自动机制）保存或恢复它了——我 会自己处理的”。
 * 例如，假设一个Login  对象包含了与一个特定的登录会话有关的信息。校验登录的合法性时，一般都想将数据保存下来，但不包括密码。
 * 为做到这一点，最简单的办法是实现Serializable，并将 password 字段设为transient。下面是具体的代码：
 *
 * 可以看到，其中的date 和username 字段保持原始状态（未设成transient），所以会自动序列化。然而， password 被设为 transient，
 * 所以不会自动保存到磁盘；另外，自动序列化机制也不会作恢复它的尝试。
 *
 * 一旦对象恢复成原来的样子，password 字段就会变成null。注意必须用 toString()检查password 是否为null，
 * 因为若用过载的“+”运算符来装配一个String 对象，而且那个运算符遇到一个null 句柄，就会造成一个名为NullPointerException 的违例（
 * 新版Java 可能会提供避免这个问题的代码）。我们也发现 date 字段被保存到磁盘，并从磁盘恢复，没有重新生成。
 * 由于Externalizable 对象默认时不保存它的任何字段，所以transient 关键字只能伴随Serializable 使用。
 *
 *
 * 2.	Externalizable 的替代方法
 * 若不是特别在意要实现 Externalizable 接口，还有另一种方法可供选用。我们可以实现 Serializable 接口，并添加（注意是“添加”，而非“覆盖”或者“实现”）名为writeObject() 和readObject()的方法。一旦对象被序列化或者重新装配，就会分别调用那两个方法。也就是说，只要提供了这两个方法，就会优先   使用它们，而不考虑默认的序列化机制。
 * 这些方法必须含有下列准确的签名：
 *
 * private void writeObject(ObjectOutputStream stream)
 * throws IOException;
 *
 * private void readObject(ObjectInputStream stream)
 * throws IOException, ClassNotFoundException
 *
 * 从设计的角度出发，情况变得有些扑朔迷离。首先，大家可能认为这些方法不属于基础类或者Serializable  接口的一部分，它们应该在自己的接口中得到定义。
 * 但请注意它们被定义成“private”，这意味着它们只能  由这个类的其他成员调用。然而，我们实际并不从这个类的其他成员中调用它们，
 * 而是由ObjectOutputStream 和ObjectInputStream 的writeObject() 及readObject()方法来调用我们对象的writeObject()  和readObject()方法
 * （注意我在这里用了很大的抑制力来避免使用相同的方法名——因为怕混淆）。大家可能奇怪 ObjectOutputStream 和ObjectInputStream 如何有权访问我们的类的
 * private 方法—
 * —只能认为这是序列化机制玩的一个把戏。
 * 在任何情况下，接口中的定义的任何东西都会自动具有public 属性，所以假若writeObject() 和readObject()必须为  private，
 * 那么它们不能成为接口（interface）的一部分。但由于我们准确地加上了签名，所以最终的效果实际与实现一个接口是相同的。
 * 看起来似乎我们调用ObjectOutputStream.writeObject()的时候，我们传递给它的Serializable 对象似乎会被检查是否实现了自己的writeObject() 。
 * 若答案是肯定的是，便会跳过常规的序列化过程，并调用writeObject()。readObject()也会遇到同样的情况。
 * 还存在另一个问题。在我们的 writeObject() 内部，可以调用defaultWriteObject()，从而决定采取默认的writeObject() 行动。
 * 类似地，在  readObject()内部，可以调用defaultReadObject()。下面这个简单的例子SerialCtl 演示了如何对一个 Serializable 对象的存储与恢复进行控制：
 */
public class Logon  implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password;
    Logon(String name, String pwd) {
        username = name;
        password = pwd;
    }
    public String toString() {
        String pwd = (password == null) ? "(n/a)" : password;
        return "logon info: \n	" + "username: " + username +
                "\n	date: " + date.toString() + "\n	password: " + pwd;
    }
    public static void main(String[] args) {
        Logon a = new Logon("Hulk", "myLittlePony");
        System.out.println( "logon a = " + a);
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Logon.out"));
            o.writeObject(a);
            o.close();
            // Delay:
            int seconds = 5;
            long t = System.currentTimeMillis() + seconds * 1000;
            while(System.currentTimeMillis() < t) ;
            // Now get them back:
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Logon.out"));
            System.out.println("Recovering object at " + new Date());
            a = (Logon)in.readObject();
            System.out.println( "logon a = " + a);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
