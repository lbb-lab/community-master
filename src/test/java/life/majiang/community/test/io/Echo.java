package life.majiang.community.test.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 9:21
 * @Description: 以Unix 首先倡导的“标准输入”、“标准输出”以及“标准错误输出”概念为基础，Java 提供了相应的System.in，System.out 以及System.err。
 * 贯这一整本书，大家都会接触到如何用 System.out 进行标准输出，它已预封装成一个 PrintStream 对象。System.err 同样是一个 PrintStream，但System.in
 * 是一个原始的InputStream，未进行任何封装处理。这意味着尽管能直接使用 System.out 和System.err，但必须事先封装System.in，否则不能从中读取数据。
 * 典型情况下，我们希望用readLine()每次读取一行输入信息，所以需要将System.in 封装到一个DataInputStream 中。这是 Java 1.0 进行行输入时采取的“老”办法。
 * 在本章稍后，大家还会看到 Java 1.1 的解决方案。下面是个简单的例子，作用是回应我们键入的每一行内容：
 */
public class Echo {
    public static void main(String[] args) {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in));
        String s;
        try {
            while((s = in.readLine()).length() != 0)
                System.out.println(s);
         // An empty line terminates the program
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
