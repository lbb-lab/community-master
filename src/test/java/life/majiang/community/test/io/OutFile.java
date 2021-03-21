package life.majiang.community.test.io;

import java.io.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 1:20
 * @Description: 利用类似的快捷方式可创建一个缓冲输出文件，用它保存数据（与由人观看的数据格式相反）：
 */
public class OutFile extends DataOutputStream {

    public OutFile(String filename) throws IOException {
        super(new BufferedOutputStream(new FileOutputStream(filename)));
    }

    public OutFile(File file) throws IOException {
        this(file.getPath());

    }


}
