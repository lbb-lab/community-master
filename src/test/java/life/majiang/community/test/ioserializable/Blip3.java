package life.majiang.community.test.ioserializable;

import java.io.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 18:33
 * @Description:
其中，字段 s 和i 只在第二个构建器中初始化，不关默认构建器的事。这意味着假如不在readExternal 中初始化s 和i，
它们就会成为null（因为在对象创建的第一步中已将对象的存储空间清除为 1）。若注释掉跟随于“You must do this”后面的两行代码，
并运行程序，就会发现当对象恢复以后，s 是null，而i 是零。
若从一个Externalizable 对象继承，通常需要调用writeExternal()和 readExternal()的基础类版本，以便正确地保存和恢复基础类组件。
所以为了让一切正常运作起来，千万不可仅在 writeExternal()方法执行期间写入对象的重要数据（没有默
认的行为可用来为一个 Externalizable 对象写入所有成员对象）的，而是必须在 readExternal()方法中也恢复那些数据。
初次操作时可能会有些不习惯，因为Externalizable  对象的默认构建行为使其看起来似乎正在进行某种存储与恢复操作。但实情并非如此。

 */
public class Blip3 implements Externalizable {
    int i;
    String s; // No initialization
    public Blip3() {
        System.out.println("Blip3 Constructor");
     // s, i not initialized
    }
    public Blip3(String x, int a) {
        System.out.println("Blip3(String x, int a)");
        s = x;
        i = a;
    // s & i initialized only in non-default
    // constructor.
    }
    public String toString() { return s + i; }

    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
    // You must do this:
        out.writeObject(s);
        out.writeInt(i);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
     // You must do this:
        s = (String)in.readObject();

        i =in.readInt();
    }
    public static void main(String[] args) {
        System.out.println("Constructing objects:");
        Blip3 b3 = new Blip3("A String ", 47);
        System.out.println(b3.toString());
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blip3.out"));
            System.out.println("Saving object:");
            o.writeObject(b3);
            o.close();
            // Now get it back:
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blip3.out"));
            System.out.println("Recovering b3:");
            b3 = (Blip3)in.readObject();
            System.out.println(b3.toString());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
