package life.majiang.community.test.chapter12.onlyread;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 16:51
 * @Description:
 */
public class Mutable {
    private int data;
    public Mutable(int initVal) {
        data = initVal;
    }
    public Mutable add(int x) {
        data += x;
        return this;
    }
    public Mutable multiply(int x) {
        data *= x;
        return this;
    }
    public Immutable2 makeImmutable2() {
        return new Immutable2(data);
    }

}
