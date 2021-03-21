package life.majiang.community.test.readerandwriter;

import java.io.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 14:47
 * @Description: 若比较第5 节和IOStreamDemo.java 中的那一小节，会注意到在这个版本中，数据是在文本之前写入的。
 * 那是由于 Java 1.1 本身存在一个错误，如下述代码所示：
 *
 * 看起来，我们在对一个 writeBytes()的调用之后写入的任何东西都不是能够恢复的。这是一个十分有限的错误，
 * 希望在你读到本书的时候已获得改正。为检测是否改正，请运行上述程序。若没有得到一个违例，而且   值都能正确打印出来，就表明已经改正。
 */
/**   Exception in thread "main" java.io.EOFException
 * 以前也遇到过这种问题，在流传输过程中是不允许被并发访问的。所以数据能接连不断的传过来，其实有很多人在运行的时候都会碰到EOFException,
 * 然后百思不得其解，去各种论坛问解决方案。其实我想说，这个异常不是必须声明的，也就是说它虽然是异常，但其实是正常运行结束的标志。
 * EOF表示读到了文件尾（ String str = dis.readUTF(); ，客户端已经断开，后面已经没有内容可以读了），发送结束自然连接也就断开了。
 */
public class IOBug {
    public static void main(String[] args) throws Exception {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Data.txt")));
        out.writeDouble(3.14159);
        out.writeBytes("That was the value of pi\n");
        out.writeBytes("This is pi/2:\n");
        out.writeDouble(3.14159/2);
        out.close();
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("Data.txt")));
        BufferedReader inbr = new BufferedReader(new InputStreamReader(in));

       // The doubles written BEFORE the line of text
       // read back correctly:
        System.out.println(in.readDouble());
       // Read the lines of text:
        System.out.println(inbr.readLine());
        System.out.println(inbr.readLine());
       // Trying to read the doubles after the line
       // produces an end-of-file exception:
        System.out.println(in.readDouble());
    }


}
