package life.majiang.community.test.chapter12.clone.check;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 12:47
 * @Description:
 */
public class IsCloneable extends Ordinary implements Cloneable {
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
