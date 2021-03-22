package life.majiang.community.test.chapter12.clone.check;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 12:48
 * @Description:
 */
public class TryMore extends NoMore {
    public Object clone() throws CloneNotSupportedException {
// Calls NoMore.clone(), throws exception:
        return super.clone();   //NoMore中的clone没有调用super.clone所以也不能对其进行clone操作

    }
}
