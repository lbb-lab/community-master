package life.majiang.community.test.chapter12.deepclone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 11:21
 * @Description:
 */
public class Int2 implements Cloneable{
    private int i;
    public Int2(int ii) { i = ii; }
    public void increment() { i++; }
    public String toString() {
        return Integer.toString(i);
    }
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Int2 can't clone");
        }
        return o;
    }

}
