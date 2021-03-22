package life.majiang.community.test.chapter12.serializable_vs_clone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 11:29
 * @Description:
 */
public class Thing3 implements Cloneable {
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Thing3 can't clone");
        }
        return o;
    }

}
