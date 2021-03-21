package life.majiang.community.test.readerandwriter;

import java.io.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 13:59
 * @Description: 大家一般看见的是转换过程非常直观，代码看起来也颇相似。但这些都不是重要的区别。最重要的是，由于随机访问文件已经改变，所以第6节未再重复。
 * 第1 节收缩了一点儿，因为假如要做的全部事情就是读取行输入，那么只需要将一个 FileReader 封装到
 * BufferedReader之内即可。第1b节展示了封装System.in，以便读取控制台输入的新方法。这里的代码量增多了一些，因为System.in 是一个 DataInputStream，
 * 而且 BufferedReader 需要一个Reader 参数，所以要用InputStreamReader 来进行转换。
 * 在2 节，可以看到如果有一个字串，而且想从中读取数据，只需用一个StringReader 替换StringBufferInputStream，剩下的代码是完全相同的。
 * 第3 节揭示了新 IO 流库设计中的一个错误。如果有一个字串，而且想从中读取数据，那么不能再以任何形式使用StringBufferInputStream。
 * 若编译一个涉及 StringBufferInputStream 的代码，会得到一条“反对” 消息，告诉我们不要用它。此时最好换用一个 StringReader。但是，
 * 假如要象第3 节这样进行格式化的内存输入，就必须使用 DataInputStream——没有什么“DataReader”可以代替它——而DataInputStream
 * 很不幸地要求用到一个 InputStream 参数。所以我们没有选择的余地，只好使用编译器不赞成的StringBufferInputStream 类。编译器同样会发出反对信息，
 * 但我们对此束手无策（注释②）。StringReader 替换StringBufferInputStream，剩下的代码是完全相同的。
 *
 * 第4 节明显是从老式数据流到新数据流的一个直接转换，没有需要特别指出的。在第 5 节中，我们被强迫使用所有的老式数据流，
 * 因为DataOutputStream 和DataInputStream 要求用到它们，而且没有可供替换的东西。然而，编译期间不会产生任何“反对”信息。若不赞成一种数据流，
 * 通常是由于它的构建器产生了一条   反对消息，禁止我们使用整个类。但在DataInputStream 的情况下，只有readLine()是不赞成使用的，因为我们最好为 readLine()
 * 使用一个 BufferedReader（但为其他所有格式化输入都使用一个DataInputStream）。
 * 若比较第5 节和IOStreamDemo.java 中的那一小节，会注意到在这个版本中，数据是在文本之前写入的。那是由于 Java 1.1 本身存在一个错误，
 *        在IOBug类中我们有展示如何错误
 */
public class NewIODemo {
    public static void main(String[] args) {
        try {
            // 1. Reading input by lines:
            BufferedReader  in = new BufferedReader(new FileReader("TEST.txt"));
            System.out.println(in);
            String s, s2 = new String();
            while((s = in.readLine())!= null)
                s2 += s + "\n";
            in.close();

            // 1b. Reading standard input:
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a line:");
            System.out.println(stdin.readLine());

            // 2. Input from memory
            StringReader in2 = new StringReader(s2);
            int c;
            while((c = in2.read()) != -1)
                System.out.print((char)c);

            // 3. Formatted memory input
            try {
                DataInputStream in3 = new DataInputStream(
                        // Oops: must use deprecated class:
                        new StringBufferInputStream(s2));
                while(true)
                    System.out.print((char)in3.readByte());
            } catch(EOFException e) {
                System.out.println("End of stream");

            }

            // 4. Line numbering & file output
            try {
                LineNumberReader li = new LineNumberReader( new StringReader(s2));
                BufferedReader in4 = new BufferedReader(li);
                PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("IODemo.out")));
                while((s = in4.readLine()) != null )
                    out1.println("Line " + li.getLineNumber() + s);
                out1.close();
            } catch(EOFException e) {
                System.out.println("End of stream");
            }

            // 5. Storing & recovering data
            try {
                DataOutputStream out2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Data.txt")));
                out2.writeDouble(3.14159);
                out2.writeBytes("That was pi");
                out2.close();
                DataInputStream in5 = new DataInputStream(new BufferedInputStream(new FileInputStream("Data.txt")));
                BufferedReader in5br = new BufferedReader(new InputStreamReader(in5));
                // Must use DataInputStream for data:
                System.out.println(in5.readDouble());
                // Can now use the "proper" readLine():
                System.out.println(in5br.readLine());
            } catch(EOFException e) {
                System.out.println("End of stream");
            }

         // 6. Reading and writing random access
         // files is the same as before.
         // (not repeated here)
        } catch(FileNotFoundException e) {
            System.out.println("File Not Found:" + args[1]);

        } catch(IOException e) {
            System.out.println("IO Exception");
        }
    }
}
