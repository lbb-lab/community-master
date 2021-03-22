package life.majiang.community.test.chapter12.clone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 10:08
 * @Description:
 */
public class MyObject implements Cloneable {
    int i;

    MyObject(int ii) { i = ii; }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("MyObject can't clone");
        }
        return o;
    }

    public String toString() {
        return Integer.toString(i);
    }

}
