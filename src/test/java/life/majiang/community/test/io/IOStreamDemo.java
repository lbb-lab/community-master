package life.majiang.community.test.io;

import java.io.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 0:43
 * @Description: 尽管库内存在大量 IO 流类，可通过多种不同的方式组合到一起，但实际上只有几种方式才会经常用到。然而，必须小心在意才能得到正确的组合。
 * 下面这个相当长的例子展示了典型IO  配置的创建与使用，可在写自己的代码时将其作为一个参考使用。注意每个配置都以一个注释形式的编号起头，
 * 并提供了适当的解释信息。
 *
 * 1.	缓冲的输入文件
 * 为打开一个文件以便输入，需要使用一个 FileInputStream，同时将一个 String 或File 对象作为文件名使用。为提高速度，最好先对文件进行缓冲处理，
 * 从而获得用于一个BufferedInputStream  的构建器的结果句柄。为了以格式化的形式读取输入数据，我们将那个结果句柄赋给用于一个 DataInputStream 的构建器。
 * DataInputStream 是我们的最终（final）对象，并是我们进行读取操作的接口。
 * 在这个例子中，只用到了readLine()方法，但理所当然任何 DataInputStream 方法都可以采用。一旦抵达文
 * 件末尾，readLine()就会返回一个null （空），以便中止并退出while 循环。
 * “String  s2”用于聚集完整的文件内容（包括必须添加的新行，因为 readLine()去除了那些行）。随后， 在本程序的后面部分中使用 s2。
 * 最后，我们调用 close()，用它关闭文件。从技术上说，会在运行finalize()时调用  close()。而且我们希望一旦程序退出，
 * 就发生这种情况（无论是否进行垃圾收集）。然 而，Java 1.0 有一个非常突出的错误（Bug），造成这种情况不会发生。在Java 1.1 中，
 * 必须明确调用System.runFinalizersOnExit(true)，用它保证会为系统中的每个对象调用finalize()。然而，最安全的方  法还是为文件明确调用 close()。
 *
 * 2.	从内存输入
 * 这一部分采用已经包含了完整文件内容的String s2，并用它创建一个 StringBufferInputStream（字串缓冲
 *
 * 输入流）——作为构建器的参数，要求使用一个 String，而非一个 StringBuffer）。随后，我们用 read() 依次读取每个字符，并将其发送至控制台。
 * 注意read()将下一个字节返回为int，所以必须将其造型为一个char，以便正确地打印。
 */
public class IOStreamDemo {
    public static void main(String[] args) { try {
        // 1. Buffered input file
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(args[0])));
        String s,
                s2 = new String();
        while((s = in.readLine())!= null)
            s2 += s + "\n";
        in.close();

        // 2. Input from memory
        StringBufferInputStream in2 = new StringBufferInputStream(s2);
        int c;
        while((c = in2.read()) != -1)
            System.out.print((char)c);

        // 3. Formatted memory input
        try {
            DataInputStream in3 = new DataInputStream(new StringBufferInputStream(s2));
            while(true)
                System.out.print((char)in3 .readByte());
        } catch(EOFException e) {
            System.out.println("End of stream encountered");
        }

        // 4. Line numbering & file output
        try {
            LineNumberInputStream li = new LineNumberInputStream(new StringBufferInputStream(s2));
            DataInputStream in4 = new DataInputStream(li);
            PrintStream out1 = new PrintStream(new BufferedOutputStream( new FileOutputStream( "IODemo.out")));
            while((s = in4.readLine()) != null )
                out1.println("Line " + li.getLineNumber() + s);
            out1.close(); // finalize() not reliable!
        } catch(EOFException e) {
            System.out.println("End of stream encountered");
        }

        // 5. Storing & recovering data
        try {
            DataOutputStream out2 = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("Data.txt")));
            out2.writeBytes("Here's the value of pi: \n");
            out2.writeDouble(3.14159);
            out2.close();
            DataInputStream in5 = new DataInputStream(new BufferedInputStream(new FileInputStream("Data.txt")));
            System.out.println(in5.readLine());
            System.out.println(in5.readDouble());
        } catch(EOFException e) {
            System.out.println("End of stream encountered");
        }

        // 6. Reading/writing random access files
        RandomAccessFile rf =new RandomAccessFile("rtest.dat", "rw");
        for(int i = 0; i < 10; i++)
            rf.writeDouble(i*1.414);
        rf.close();

        rf = new RandomAccessFile("rtest.dat", "rw"); rf.seek(5*8);
        rf.writeDouble(47.0001); rf.close();

        rf = new RandomAccessFile("rtest.dat", "r");
        for(int i = 0; i < 10; i++)
            System.out.println("Value " + i + ": " + rf.readDouble());
        rf.close();

// 7. File input shorthand
//        InFile in6 = new InFile(args[0]);
//        String s3 = new String();
//        System.out.println("First line in file: " + in6.readLine());
//        in6.close();

// 8. Formatted file output shorthand
//        PrintFile out3 = new PrintFile("Data2.txt");
//        out3.print("Test of PrintFile");
//        out3.close();

// 9. Data file output shorthand
//        OutFile out4 = new OutFile("Data3.txt");
//        out4.writeBytes("Test of outDataFile\n\r");
//        out4.writeChars("Test of outDataFile\n\r");
//        out4.close();

    } catch(FileNotFoundException e) {
        System.out.println(
                "File Not Found:" + args[0]);
    } catch(IOException e) {
        System.out.println("IO Exception");
    }
    }



}
