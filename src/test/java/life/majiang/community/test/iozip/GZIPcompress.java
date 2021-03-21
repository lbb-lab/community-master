package life.majiang.community.test.iozip;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 15:46
 * @Description:   压缩类的用法非常直观——只需将输出流封装到一个GZIPOutputStream 或者ZipOutputStream 内，
 * 并将输入流封装到GZIPInputStream 或者ZipInputStream 内即可。剩余的全部操作就是标准的IO 读写。然而，这是一个很典型的例子，
 * 我们不得不混合使用新旧 IO 流：数据的输入使用Reader 类，而 GZIPOutputStream 的构建器只能接收一个 OutputStream 对象，
 * 不能接收Writer 对象。
 */
public class GZIPcompress {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("TEST.txt"));
            BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("test.gz")));
            System.out.println("Writing file");
            int c;
            while((c = in.read()) != -1)
                out.write(c);
            in.close();
            out.close();
            System.out.println("Reading file");
            BufferedReader in2 = new BufferedReader(new InputStreamReader( new GZIPInputStream(new FileInputStream("test.gz"))));
            String s;
            while((s = in2.readLine()) != null)
                System.out.println(s);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
