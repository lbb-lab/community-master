package life.majiang.community.test.chapter12.clone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 9:56
 * @Description:
 */
public class Int {
    private int i;
    public Int(int ii) { i = ii; }
    public void increment() { i++; }
    public String toString() {
        return Integer.toString(i);
    }
}
