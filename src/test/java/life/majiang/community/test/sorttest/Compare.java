package life.majiang.community.test.sorttest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/16 14:08
 * @Description:
 */
public interface Compare {
    boolean lessThan(Object lhs, Object rhs);
    boolean lessThanOrEqual(Object lhs, Object rhs);
}
