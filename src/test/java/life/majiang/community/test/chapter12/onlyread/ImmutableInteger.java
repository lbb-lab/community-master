package life.majiang.community.test.chapter12.onlyread;

import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 16:28
 * @Description:
 */
public class ImmutableInteger {
    public static void main(String[] args) {
        Vector v = new Vector();
        for(int i = 0; i < 10; i++)
            v.addElement(new Integer(i));
// But how do you change the int

// inside the Integer?
    }

}
