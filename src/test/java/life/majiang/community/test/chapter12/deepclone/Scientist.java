package life.majiang.community.test.chapter12.deepclone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 11:40
 * @Description:
 */
public class Scientist extends Person implements Cloneable {
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
// this should never happen:
// It's Cloneable already!
            throw new InternalError();
        }
    }
}