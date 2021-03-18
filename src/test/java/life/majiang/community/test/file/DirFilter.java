package life.majiang.community.test.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 21:53
 * @Description:
 */
public class DirFilter implements FilenameFilter {
    String afn;
    DirFilter(String afn) {
        this.afn = afn;
    }

    public boolean accept(File dir, String name) {
// Strip path information:
        String f = new File(name).getName();
        return f.indexOf(afn) != -1;
    }

}
