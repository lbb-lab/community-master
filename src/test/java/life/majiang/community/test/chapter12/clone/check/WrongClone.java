package life.majiang.community.test.chapter12.clone.check;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 12:46
 * @Description:
 */
public class WrongClone extends Ordinary{
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Throws exception
    }

}
