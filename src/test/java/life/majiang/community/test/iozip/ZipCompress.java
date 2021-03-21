package life.majiang.community.test.iozip;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 15:56
 * @Description: 对于要加入压缩档的每一个文件，都必须调用 putNextEntry()，并将其传递给一个 ZipEntry 对象。ZipEntry
 * 对象包含了一个功能全面的接口，利用它可以获取和设置 Zip 文件内那个特定的 Entry（入口）上能够接受的所有数据：
 * 名字、压缩后和压缩前的长度、日期、CRC   校验和、额外字段的数据、注释、压缩方法以及它是否一个目录入口等等。
 * 然而，虽然 Zip 格式提供了设置密码的方法，但Java 的Zip 库没有提供这方面的支持。而且尽管 CheckedInputStream 和CheckedOutputStream
 * 同时提供了对Adler32 和CRC32 校验和的支持，但是ZipEntry 只支持 CRC 的接口。这虽然属于基层 Zip 格式的限制，
 * 但却限制了我们使用速度更快的Adler32。
 * 为解压文件，ZipInputStream 提供了一个 getNextEntry()方法，能在有的前提下返回下一个ZipEntry。作为一个更简洁的方法，
 * 可以用 ZipFile 对象读取文件。该对象有一个 entries()方法，可以为 ZipEntry 返回一个Enumeration（枚举）。
 * 为读取校验和，必须多少拥有对关联的Checksum 对象的访问权限。在这里保留了指向CheckedOutputStream
 * 和CheckedInputStream 对象的一个句柄。但是，也可以只占有指向Checksum 对象的一个句柄。
 * Zip   流中一个令人困惑的方法是setComment()。正如前面展示的那样，我们可在写一个文件时设置注释内 容，
 * 但却没有办法取出 ZipInputStream 内的注释。看起来，似乎只能通过 ZipEntry 逐个入口地提供对注释的完全支持。
 * 当然，使用 GZIP 或Zip 库时并不仅仅限于文件——可以压缩任何东西，包括要通过网络连接发送的数据。
 */
public class ZipCompress {
    public static void main(String[] args) {
        try {
            FileOutputStream f = new FileOutputStream("test.zip");
            CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(csum));
            out.setComment("A test of Java Zipping");

            // Can't read the above comment, though
            for(int i = 0; i < args.length; i++) {
                System.out.println("Writing file " + args[i]);
                BufferedReader in = new BufferedReader(new FileReader(args[i]));
                out.putNextEntry(new ZipEntry(args[i]));
                int c;
                while((c = in.read()) != -1)
                    out.write(c);
                in.close();
            }
            out.close();

            // Checksum valid only after the file
            // has been closed!
            System.out.println("Checksum: " + csum.getChecksum().getValue());

            // Now extract the files:
            System.out.println("Reading file");
            FileInputStream fi = new FileInputStream("test.zip");
            CheckedInputStream csumi = new CheckedInputStream( fi, new Adler32());
            ZipInputStream in2 = new ZipInputStream(new BufferedInputStream(csumi));
            ZipEntry ze;
            System.out.println("Checksum: " + csumi.getChecksum().getValue());
            while((ze = in2.getNextEntry()) != null) {
                System.out.println("Reading file " + ze);
                int x;
                while((x = in2.read()) != -1)
                    System.out.write(x);
            }
            in2.close();

            // Alternative way to open and read
            // zip files:
            ZipFile zf = new ZipFile("test.zip");
            Enumeration e = zf.entries();
            while(e.hasMoreElements()) {
                ZipEntry ze2 = (ZipEntry)e.nextElement();
                System.out.println("File: " + ze2);
                // ... and extract the data as before
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
