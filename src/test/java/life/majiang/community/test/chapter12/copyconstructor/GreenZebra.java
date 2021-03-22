package life.majiang.community.test.chapter12.copyconstructor;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 15:09
 * @Description:
 *     克隆看起来要求进行非常复杂的设置，似乎还该有另一种替代方案。一个办法是制作特殊的构建器，
 * 令其负 责复制一个对象。在C++中，这叫作“副本构建器”。刚开始的时候，这好象是一种非常显然的解决方案
 * （如果你是 C++程序员，这个方法就更显亲切）。下面是一个实际的例子：
 *
 *这个例子第一眼看上去显得有点奇怪。不同水果的质量肯定有所区别，但为什么只是把代表那些质量的数据   成员直接置入Fruit（水果）类？
 * 有两方面可能的原因。第一个是我们可能想简便地插入或修改质量。注意Fruit 有一个protected（受到保护的）addQualities()方法，
 * 它允许衍生类来进行这些插入或修改操作（大家或许会认为最合乎逻辑的做法是在Fruit 中使用一个 protected 构建器，用它获取 FruitQualities 参数，
 * 但构建器不能继承，所以不可在第二级或级数更深的类中使用它）。通过将水果的质量置入一个独立的类，   可以得到更大的灵活性，
 * 其中包括可以在特定 Fruit 对象的存在期间中途更改质量。
 * 之所以将FruitQualities 设为一个独立的对象，另一个原因是考虑到我们有时希望添加新的质量，或者通过继承与多形性改变行为。
 * 注意对GreenZebra 来说（这实际是西红柿的一类——我已栽种成功，它们简直令人难以置信），构建器会调用addQualities()，
 * 并为其传递一个 ZebraQualities 对象。该对象是从FruitQualities 衍生出来的，所以能与基础类中的 FruitQualities 句柄联系在一起。
 * 当然，一旦GreenZebra 使用FruitQualities，就必须将其下溯造型成为正确的类型（就象 evaluate()中展示的那样），
 * 但它肯定知道类型是ZebraQualities。大家也看到有一个 Seed （种子）类，Fruit（大家都知道，水果含有自己的种子）包含了一个 Seed 数组。
 * 最后，注意每个类都有一个副本构建器，而且每个副本构建器都必须关心为基础类和成员对象调用副本构建 器的问题，从而获得“深层复制”的效果。
 * 对副本构建器的测试是在 CopyConstructor 类内进行的。方法ripen()需要获取一个 Tomato 参数，并对其执行副本构建工作，以便复制对象：
 * t = new Tomato(t);而slice()需要获取一个更常规的 Fruit 对象，而且对它进行复制： f = new Fruit(f);
 * 它们都在main()中伴随不同种类的 Fruit 进行测试。下面是输出结果：
 * In ripen, t is a Tomato
 * In slice, f is a Fruit
 * In ripen, t is a Tomato
 * In slice, f is a Fruit
 *
 * 从中可以看出一个问题。在slice()内部对 Tomato 进行了副本构建工作以后，结果便不再是一个 Tomato 对象，而只是一个Fruit。
 * 它已丢失了作为一个 Tomato（西红柿）的所有特征。此外，如果采用一个GreenZebra，ripen()和slice()会把它分别转换成一个
 * Tomato 和一个 Fruit。所以非常不幸，假如想制作对象的一个本地副本，Java 中的副本构建器便不是特别适合我们。
 */
public class GreenZebra extends Tomato {
    GreenZebra() {
        addQualities(new ZebraQualities());
    }

    GreenZebra(GreenZebra g) {
        super(g); // Calls Tomato(Tomato)
        // Restore the right qualities:
        addQualities(new ZebraQualities());
    }

    void evaluate() {
        ZebraQualities zq = (ZebraQualities)getQualities();
        // Do something with the qualities
        // ...
    }

}
