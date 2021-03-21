package life.majiang.community.test.io;

import java.io.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 1:11
 * @Description: 无论构建器的String 版本还是File 版本都包括在内，用于共同创建一个 FileInputStream。就象这个例子展示的那样，
 * 现在可以有效减少创建文件时由于重复强调造成的问题。
 */
public class InFile extends DataInputStream {

    public InFile(String filename) throws FileNotFoundException {
        super(new BufferedInputStream(new FileInputStream(filename)));
    }

    public InFile(File file) throws FileNotFoundException {
        this(file.getPath());
    }

}
