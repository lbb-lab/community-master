package life.majiang.community.test.chapter12.copyconstructor;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 15:06
 * @Description:
 */
public class ZebraQualities extends FruitQualities {
    private int stripedness;
    ZebraQualities() { // Default constructor
// do something meaningful...
    }
    ZebraQualities(ZebraQualities z) { super(z);

        stripedness = z.stripedness;
    }

}
