package life.majiang.community.test.chapter12.copyconstructor;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 15:05
 * @Description:
 */
public class Tomato extends Fruit{
    Tomato() {
        super(new FruitQualities(), 100);
    }
    Tomato(Tomato t) {
        // Copy-constructor
        super(t);
        // Upcast for base copy-constructor
        // Other copy-construction activities...
    }

}
