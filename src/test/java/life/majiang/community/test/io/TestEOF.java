package life.majiang.community.test.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 1:04
 * @Description: StringBufferInputStream 的接口是有限的，所以通常需要将其封装到一个DataInputStream 内，从而增强它的能力。
 * 然而，若选择用readByte()每次读出一个字符，那么所有值都是有效的，所以不可再用返回值来  侦测何时结束输入。相反，
 * 可用available()方法判断有多少字符可用。下面这个例子展示了如何从文件中   一次读出一个字符：
 *
 * 注意取决于当前从什么媒体读入，avaiable()的工作方式也是有所区别的。它在字面上意味着“可以不受阻 塞读取的字节数量”。
 * 对一个文件来说，它意味着整个文件。但对一个不同种类的数据流来说，它却可能有  不同的含义。因此在使用时应考虑周全。
 * 为了在这样的情况下侦测输入的结束，也可以通过捕获一个违例来实现。然而，若真的用违例来控制数据 流，却显得有些大材小用。
 *
 * 这个例子展示了如何LineNumberInputStream  来跟踪输入行的编号。在这里，不可简单地将所有构建器都组合起来，
 * 因为必须保持 LineNumberInputStream 的一个句柄（注意这并非一种继承环境，所以不能简单地将in4 造型到一个 LineNumberInputStream）。
 * 因此，li 容纳了指向 LineNumberInputStream 的句柄，然后在它的基础上创建一个DataInputStream，以便读入数据。
 * 这个例子也展示了如何将格式化数据写入一个文件。首先创建了一个 FileOutputStream，用它同一个文件连
 * 接。考虑到效率方面的原因，它生成了一个BufferedOutputStream。这几乎肯定是我们一般的做法，但却必 须明确地这样做。
 * 随后为了进行格式化，它转换成一个PrintStream。用这种方式创建的数据文件可作为一   个原始的文本文件读取。
 * 标志DataInputStream 何时结束的一个方法是 readLine()。一旦没有更多的字串可以读取，它就会返回
 * null。每个行都会伴随自己的行号打印到文件里。该行号可通过li 查询。
 * 可看到用于 out1 的、一个明确指定的close()。若程序准备掉转头来，并再次读取相同的文件，这种做法就显得相当有用。然而，
 * 该程序直到结束也没有检查文件IODemo.txt。正如以前指出的那样，如果不为自己的 所有输出文件调用 close()，就可能发现缓冲区不会得到刷新，造成它们不完整。
 */
public class TestEOF {
    public static void main(String[] args) {
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("TestEof.java")));
            while(in.available() != 0)
                System.out.print((char)in.readByte());
        } catch (IOException e) {
            System.err.println("IOException");
        }
    }

}
