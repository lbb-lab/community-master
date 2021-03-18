package life.majiang.community.test.file;

import java.util.Comparator;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 0:04
 * @Description:
 */
public class MyCompare implements Comparator {
    public int compare(Object o1, Object o2) {
        if((((String)(o1)).toLowerCase().indexOf(0)) > (((String)(o2)).toLowerCase().indexOf(0))){  ////这样比较是降序,如果把-1改成1就是升序.
            return -1;
        } else if((((String)o1).toLowerCase().indexOf(0)) < ((String)(o2)).toLowerCase().indexOf(0)){
            return 1;
        } else {
            return 0;
        }
    }

}
