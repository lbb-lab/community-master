package life.majiang.community.test.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Comparator;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 23:42
 * @Description:
 */
public class SortedDirList {
    private File path;
    private String[] list;

    public SortedDirList(final String afn) {
        path = new File(".");
        if(afn == null)
            list = path.list();
        else
            list = path.list(
                    new FilenameFilter() {
                        public boolean accept(File dir, String n) {
                            String f = new File(n).getName();
                            return f.indexOf(afn) != -1;
                        }
                    });
        sort();
    }

    void print() {
        for(int i = 0; i < list.length; i++)
            System.out.println(list[i]);
    }

    private void sort() {
        StrSortVector sv = new StrSortVector();
        for(int i = 0; i < list.length; i++)
            sv.addElement(list[i]);
        // The first time an element is pulled from

        // the StrSortVector the list is sorted:
        sv.sort(new MyCompare());
        for(int i = 0; i < list.length; i++)
            list[i] = (String) sv.elementAt(i);
    }

    // Test it:
    public static void main(String[] args) { SortedDirList sd;
        if(args.length == 0)
            sd = new SortedDirList(null); else
            sd = new SortedDirList(args[0]);
        sd.print();
    }

}
