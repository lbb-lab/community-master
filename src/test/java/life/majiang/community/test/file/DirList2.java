package life.majiang.community.test.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 23:11
 * @Description:  注意filter() 的自变量必须是final。这一点是匿名内部类要求的，使其能使用来自本身作用域以外的一个对象。
 */
public class DirList2 {
    public static FilenameFilter filter(final String afn) {
        // Creation of anonymous inner class:
        return new FilenameFilter() {
            String fn = afn;

            public boolean accept(File dir, String n) {
                // Strip path information:
                String f = new File(n).getName();
                return f.indexOf(fn) != -1;
            }
        }; // End of anonymous inner class

    }

    public static void main(String[] args) { try {
        File path = new File(".");
        String[] list;
        if(args.length == 0)
            list = path.list();
        else
            list = path.list(filter(args[0]));
        for(int i = 0; i < list.length; i++)
            System.out.println(list[i]);
    } catch(Exception e) {
        e.printStackTrace();
    }
    }

}
