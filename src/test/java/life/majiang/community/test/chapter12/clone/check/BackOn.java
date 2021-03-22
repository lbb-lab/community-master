package life.majiang.community.test.chapter12.clone.check;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 12:49
 * @Description:
 */
public class BackOn extends NoMore {
    private BackOn duplicate(BackOn b) {
// Somehow make a copy of b
// and return that copy. This is a dummy
// copy, just to make the point:
        return new BackOn();
    }
    public Object clone() {
// Doesn't call NoMore.clone():
        return duplicate(this);
    }

}
