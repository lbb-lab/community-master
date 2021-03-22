package life.majiang.community.test.chapter12.clone.check;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 12:47
 * @Description:
 */
public class NoMore extends IsCloneable {
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();  //没有调用super.clone所以不能对其进行clone操作
    }

}
