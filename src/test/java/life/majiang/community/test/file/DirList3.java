package life.majiang.community.test.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 23:21
 * @Description: main()现在的自变量是 final，因为匿名内部类直接使用args[0]。
 * 这展示了如何利用匿名内部类快速创建精简的类，以便解决一些复杂的问题。由于Java 中的所有东西都与类有关，所以它无疑是一种相当有用的编码技术。
 * 它的一个好处是将特定的问题隔离在一个地方统一解决。但   在另一方面，这样生成的代码不是十分容易阅读，所以使用时必须慎重。
 */
public class DirList3 {
    public static void main(final String[] args) {
        try {
            File path = new File(".");
            String[] list;
            if(args.length == 0)
                list = path.list();
            else
                list = path.list(new FilenameFilter() {
                    public boolean accept(File dir, String n) {
                        String f = new File(n).getName();
                        return f.indexOf(args[ 0]) != -1;
                    }
                });
            for(int i = 0; i < list.length; i++)
                System.out.println(list[i]);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
