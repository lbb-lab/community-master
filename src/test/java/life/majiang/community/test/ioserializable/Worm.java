package life.majiang.community.test.ioserializable;

import java.io.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 16:34
 * @Description: Java 1.1 增添了一种有趣的特性，名为“对象序列化”（Object Serialization）。它面向那些实现了Serializable 接口的对象，
 * 可将它们转换成一系列字节，并可在以后完全恢复回原来的样子。这一过程亦可通过网络进行。这意味着序列化机制能自动补偿操作系统间的差异。
 * 换句话说，可以先在Windows 机器上创建一个对象，对其序列化，然后通过网络发给一台 Unix 机器，然后在那里准确无误地重新“装配”。
 * 不必关心数据在不同机器上如何表示，也不必关心字节的顺序或者其他任何细节。
 * 就其本身来说，对象的序列化是非常有趣的，因为利用它可以实现“有限持久化”。请记住“持久化”意味  着对象的“生存时间”并不取决于程序
 * 是否正在执行——它存在或“生存”于程序的每一次调用之间。通过  序列化一个对象，将其写入磁盘，以后在程序重新调用时重新恢复那个对象，
 * 就能圆满实现一种“持久”效  果。之所以称其为“有限”，是因为不能用某种“persistent”（持久）关键字简单地地定义一个对象，并
 * 让系统自动照看其他所有细节问题（尽管将来可能成为现实）。相反，必须在自己的程序中明确地序列化和  组装对象。
 * 语言里增加了对象序列化的概念后，可提供对两种主要特性的支持。Java 1.1 的“远程方法调用”（RMI）
 *
 * 使本来存在于其他机器的对象可以表现出好象就在本地机器上的行为。将消息发给远程对象时，需要通过对 象序列化来传输参数和返回值。
 * RMI 将在第 15 章作具体讨论。
 * 对象的序列化也是 Java Beans 必需的，后者由Java 1.1 引入。使用一个Bean 时，它的状态信息通常在设计期间配置好。程序启动以后，
 * 这种状态信息必须保存下来，以便程序启动以后恢复；具体工作由对象序列化    完成。
 * 对象的序列化处理非常简单，只需对象实现了 Serializable 接口即可（该接口仅是一个标记，没有方法）。在Java 1.1 中，许多标准库类都发生了
 * 改变，以便能够序列化——其中包括用于基本数据类型的全部封装器、所有集合类以及其他许多东西。甚至 Class 对象也可以序列化（第 11 章讲述了具体实现过程）。
 * 为序列化一个对象，首先要创建某些OutputStream 对象，然后将其封装到 ObjectOutputStream 对象内。此
 * 时，只需调用writeObject() 即可完成对象的序列化，并将其发送给OutputStream。相反的过程是将一个InputStream 封装到 ObjectInputStream 内，
 * 然后调用 readObject()。和往常一样，我们最后获得的是指向一个上溯造型Object 的句柄，所以必须下溯造型，以便能够直接设置。
 * 对象序列化特别“聪明”的一个地方是它不仅保存了对象的“全景图”，而且能追踪对象内包含的所有句柄   并保存那些对象；
 * 接着又能对每个对象内包含的句柄进行追踪；以此类推。我们有时将这种情况称为“对象   网”，单个对象可与之建立连接。
 * 而且它还包含了对象的句柄数组以及成员对象。若必须自行操纵一套对象   序列化机制，那么在代码里追踪所有这些链接时可能会显得非常麻烦。
 * 在另一方面，由于Java  对象的序列化似乎找不出什么缺点，所以请尽量不要自己动手，让它用优化的算法自动维护整个对象网。下面这个例子对
 * 序列化机制进行了测试。它建立了许多链接对象的一个“Worm”（蠕虫），每个对象都与Worm 中的下一段链接，同时又与属于不同类（Data）
 * 的对象句柄数组链接：
 *
 * 更有趣的是，Worm 内的Data 对象数组是用随机数字初始化的（这样便不用怀疑编译器保留了某种原始信息）。每个 Worm 段都用一个 Char 标记。
 * 这个 Char 是在重复生成链接的 Worm 列表时自动产生的。创建一个Worm 时，需告诉构建器希望它有多长。为产生下一个句柄（next），
 * 它总是用减去 1 的长度来调用Worm 构建器。最后一个next 句柄则保持为null（空），表示已抵达 Worm 的尾部。
 * 上面的所有操作都是为了加深事情的复杂程度，加大对象序列化的难度。然而，真正的序列化过程却是非常    简单的。一旦从另外某个流里创建了
 * ObjectOutputStream ，writeObject() 就会序列化对象。注意也可以为一个String 调用writeObject() 。亦可使用与DataOutputStream
 * 相同的方法写入所有基本数据类型（它们有相同的接口）。有两个单独的try 块看起来是类似的。第一个读写的是文件，而另一个读写的是一个
 * ByteArray（字节数组）。可利用对任何DataInputStream 或者DataOutputStream 的序列化来读写特定的对象；正如在关于连网的那一章会讲到的那样，
 * 这些对象甚至包括网络。一次循环后的输出结果如下：
 *
 * 可以看出，装配回原状的对象确实包含了原来那个对象里包含的所有链接。
 * 注意在对一个Serializable（可序列化）对象进行重新装配的过程中，不会调用任何构建器（甚至默认构建 器）。
 * 整个对象都是通过从InputStream 中取得数据恢复的。作为Java 1.1 特性的一种，我们注意到对象的序列化并不属于新的
 * Reader 和Writer 层次结构的一部分，而是沿用老式的InputStream 和OutputStream 结构。所以在一些特殊的场合下，
 * 不得不混合使用两种类型的层次结构。
 */
public class Worm {
    // Generate a random int value:
    private static int r() {
        return (int)(Math.random() * 10);
    }
    private Data[] d = {new Data(r()), new Data(r()), new Data(r())};
    private Worm next;
    private char c;
    // Value of i == number of segments
    Worm(int i, char x) {
        System.out.println(" Worm constructor: " + i);
        c = x;
        if(--i > 0)
            next = new Worm(i, (char)(x + 1));
    }

    Worm() {
        System.out.println("Default constructor");
    }

    public String toString() {
        String s = ":" + c + "(";
        for(int i = 0; i < d.length; i++)
            s += d[i].toString();
        s += ")";
        if(next != null)
            s += next.toString();
        return s;
    }

    public static void main(String[] args) {
        Worm w = new Worm(6, 'a');
        System.out.println("w = " + w);

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm.out"));
            out.writeObject("Worm storage");
            out.writeObject(w);
            out.close(); // Also flushes output
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.out"));
            String s = (String)in.readObject();
            Worm w2 = (Worm)in.readObject();
            System.out.println(s + ", w2 = " + w2);
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject("Worm storage");
            out.writeObject(w);
            out.flush();
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream( bout.toByteArray()));
            String s = (String)in.readObject();
            Worm w3 = (Worm)in.readObject();
            System.out.println(s + ", w3 = " + w3);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
