package life.majiang.community.test.rtti;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 23:41
 * @Description: 基础类可编码成一个interface（接口）、一个abstract（抽象）类或者一个普通类。由于 Shape 没有真正的成员（亦即有定义的成员），
 * 而且并不在意我们创建了一个纯粹的 Shape 对象，所以最适合和最灵活的表达方式便是用一个接口。而且由于不必设置所有那些abstract 关键字，
 * 所以整个代码也显得更为清爽。
 * 每个衍生类都覆盖了基础类draw 方法，所以具有不同的行为。在main()中创建了特定类型的Shape，然后将其添加到一个Vector。这里正是上溯造型发生的地方，
 * 因为Vector 只容纳了对象。由于Java 中的所有东西（除基本数据类型外）都是对象，所以Vector 也能容纳 Shape 对象。但在上溯造型至 Object 的过程中，
 * 任何特殊的信息都会丢失，其中甚至包括对象是几何形状这一事实。对 Vect or 来说，它们只是Object。用nextElement() 将一个元素从 Vector 提取出来的时候，
 * 情况变得稍微有些复杂。由于 Vector 只容纳Object，所以 nextElement() 会自然地产生一个 Object 句柄。但我们知道它实际是个 Shape 句柄，
 * 而且希望将Shape 消息发给那个对象。所以需要用传统的"(Shape)"方式造型成一个Shape。这是 RTTI 最基本的形式，因为在 Java 中，所有造型都会在运行期间
 * 得到检查，以确保其正确性。那正是RTTI 的意义所在：在运行期，对象的类型会得到鉴定。
 * 在目前这种情况下，RTTI 造型只实现了一部分：Object 造型成 Shape，而不是造型成Circle，Square 或者Triangle。那是由于我们目前能够肯定的唯一事实
 * 就是 Vector 里充斥着几何形状，而不知它们的具体类别。在编译期间，我们肯定的依据是我们自己的规则；而在编译期间，却是通过造型来肯定这一点。
 * 现在的局面会由多形性控制，而且会为Shape 调用适当的方法，以便判断句柄到底是提供Circle，Square，
 * 还是提供给 Triangle。而且在一般情况下，必须保证采用多形性方案。因为我们希望自己的代码尽可能少知道一些与对象的具体类型有关的情况，
 * 只将注意力放在某一类对象（这里是Shape）的常规信息上。只有这  样，我们的代码才更易实现、理解以及修改。所以说多形性是面向对象程序设计的一个常规目标。
 * 然而，若碰到一个特殊的程序设计问题，只有在知道常规句柄的确切类型后，才能最容易地解决这个问题， 这个时候又该怎么办呢？举个例子来说，
 * 我们有时候想让自己的用户将某一具体类型的几何形状（如三角
 * 形）全都变成紫色，以便突出显示它们，并快速找出这一类型的所有形状。此时便要用到RTTI 技术，用它查询某个 Shape 句柄引用的准确类型是什么。
 */
public class Shapes {
    public static void main(String[] args) {
        Vector s = new Vector();
        s.addElement(new Circle());
        s.addElement(new Square());
        s.addElement(new Triangle());
        Enumeration e = s.elements();
        while(e.hasMoreElements())
        ((Shape)e.nextElement()).draw();
    }

}
