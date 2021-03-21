package life.majiang.community.test.io;

import java.io.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 1:14
 * @Description: 注意构建器不可能捕获一个由基础类构建器“掷”出的违例。
 */
public class PrintFile extends PrintStream {
    public PrintFile(String filename) throws IOException {
        super(new BufferedOutputStream(new FileOutputStream(filename)));
    }

    public PrintFile(File file) throws IOException {
        this(file.getPath());
    }

}
