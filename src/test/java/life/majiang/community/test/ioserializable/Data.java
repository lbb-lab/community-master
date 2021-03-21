package life.majiang.community.test.ioserializable;

import java.io.Serializable;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 16:19
 * @Description:
 */
public class Data implements Serializable {
    private int i;

    Data(int x) { i = x; }

    public String toString() {
        return Integer.toString(i);
    }

}
