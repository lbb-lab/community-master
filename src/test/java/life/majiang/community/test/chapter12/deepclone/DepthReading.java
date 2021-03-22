package life.majiang.community.test.chapter12.deepclone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 11:12
 * @Description:
 */
public class DepthReading implements Cloneable {
    private double depth;
    public DepthReading(double depth) {
        this.depth = depth;
    }
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

}
