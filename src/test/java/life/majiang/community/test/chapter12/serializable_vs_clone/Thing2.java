package life.majiang.community.test.chapter12.serializable_vs_clone;

import java.io.Serializable;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 11:29
 * @Description:
 */
public class Thing2 implements Serializable {
    Thing1 o1 = new Thing1();
}
