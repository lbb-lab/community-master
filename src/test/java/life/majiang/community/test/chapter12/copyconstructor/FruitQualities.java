package life.majiang.community.test.chapter12.copyconstructor;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 14:59
 * @Description:
 */
public class FruitQualities {
    private int weight;
    private int color;
    private int firmness;
    private int ripeness;
    private int smell;
     // etc.
    FruitQualities() { // Default constructor
    // do something meaningful...
    }
    // Other constructors:
    // ...
    // Copy constructor:

    FruitQualities(FruitQualities f) {
        weight = f.weight;
        color = f.color;
        firmness = f.firmness;
        ripeness = f.ripeness;
        smell = f.smell;
        // etc.
    }

}
