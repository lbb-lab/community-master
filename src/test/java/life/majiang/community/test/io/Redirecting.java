package life.majiang.community.test.io;

import java.io.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 15:04
 * @Description: 这个程序的作用是将标准输入同一个文件连接起来，并将标准输出和错误重定向至另一个文件。   这是不可避免会遇到“反对”消息的另一个例子。
 * 用-deprecation 标志编译时得到的消息如下：
 * Note:The constructor java.io.PrintStream(java.io.OutputStream) has been deprecated. 注意：不推荐使用构建器java.io.PrintStream（java.io.OutputStream）。
 *
 * 然而，无论 System.setOut()还是System.setErr()都要求用一个 PrintStream 作为参数使用，所以必须调用PrintStream 构建器。所以大家可能会觉得奇怪，
 * 既然 Java 1.1 通过反对构建器而反对了整个PrintStream，为什么库的设计人员在添加这个反对的同时，依然为 System 添加了新方法，且指明要求用PrintStream，
 * 而不是用 PrintWriter 呢？毕竟，后者是一个崭新和首选的替换措施呀？这真令人费解。
 */
public class Redirecting {
    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream( "README.MD"));
            // Produces deprecation message:
            PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));
            System.setIn(in);
            System.setOut(out);
            System.setErr(out);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(br);
            String s;
            while((s = br.readLine()) != null)
                System.out.println(s);
            out.close(); // Remember this!
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
