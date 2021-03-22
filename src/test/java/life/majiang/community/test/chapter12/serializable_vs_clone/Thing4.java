package life.majiang.community.test.chapter12.serializable_vs_clone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 11:30
 * @Description:
 */
public class Thing4 implements Cloneable {
    Thing3 o3 = new Thing3();
    public Object clone() {
        Thing4 o = null;
        try {
            o = (Thing4)super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Thing4 can't clone");
        }
// Clone the field, too:
        o.o3 = (Thing3)o3.clone();
        return o;
    }

}
