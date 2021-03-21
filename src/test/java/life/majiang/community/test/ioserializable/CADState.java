package life.majiang.community.test.ioserializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 19:51
 * @Description: Shape（几何形状）类“实现了可序列化”（implements  Serializable），所以从 Shape 继承的任何东西也都会自动“可序列化”。
 * 每个 Shape 都包含了数据，而且每个衍生的Shape 类都包含了一个特殊的 static 字段，用于决定所有那些类型的 Shape 的颜色
 * （如将一个 static 字段置入基础类，结果只会产生一个字段，因为static 字段未在衍生类中复制）。可对基础类中的方法进行覆盖处理，
 * 以便为不同的类型设置颜色
 *
 * （static 方法不会动态绑定，所以这些都是普通的方法）。每次调用randomFactory()方法时，它都会创建一个不同的 Shape（Shape 值采用随机值）。
 * Circle（圆）和 Square（矩形）属于对Shape 的直接扩展；唯一的差别是Circle 在定义时会初始化颜色， 而Square 在构建器中初始化。Line（直线）
 * 的问题将留到以后讨论。
 * 在main()中，一个Vector 用于容纳Class 对象，而另一个用于容纳形状。若不提供相应的命令行参数，就
 * 会创建 shapeTypes Vector，并添加Class 对象。然后创建shapes Vector ，并添加Shape 对象。接下来，所有static color 值都会设成 GREEN，
 * 而且所有东西都会序列化到文件 CADState.out。
 * 若提供了一个命令行参数（假设CADState.out），便会打开那个文件，并用它恢复程序的状态。无论在哪种 情况下，结果产生的Shape 的Vector
 * 都会打印出来。下面列出它某一次运行的结果：
 *
 * 从中可以看出，xPos，yPos 以及dim 的值都已成功保存和恢复出来。但在获取 static 信息时却出现了问题。所有“3”都已进入，
 * 但没有正常地出来。Circle 有一个 1 值（定义为 RED），而 Square 有一个 0 值
 * （记住，它们是在构建器里初始化的）。看上去似乎static 根本没有得到初始化！实情正是如此——尽管类
 * Class 是“可以序列化的”，但却不能按我们希望的工作。所以假如想序列化static 值，必须亲自动手。这正是 Line
 * 中的serializeStaticState() 和deserializeStaticState()两个static 方法的用途。可以看到，这两个方法都是作为存储和恢复进程
 * 的一部分明确调用的（注意写入序列化文件和从中读回的顺序不能    改变）。所以为了使CADState.java 正确运行起来，必须采用下述三种方法之一：
 * (1)	为几何形状添加一个serializeStaticState() 和deserializeStaticState()。
 * (2)	删除Vector shapeTypes 以及与之有关的所有代码
 * (3)	在几何形状内添加对新序列化和撤消序列化静态方法的调用
 * 要注意的另一个问题是安全，因为序列化处理也会将private 数据保存下来。若有需要保密的字段，应将其标记成 transient。
 * 但在这之后，必须设计一种安全的信息保存方法。这样一来，一旦需要恢复，就可以重设那些 private 变量。
 */
public class CADState {
    public static void main(String[] args) throws Exception {
        Vector shapeTypes, shapes;
        if(args.length == 0) {
            shapeTypes = new Vector();
            shapes = new Vector();
// Add handles to the class objects:
            shapeTypes.addElement(Circle.class);
            shapeTypes.addElement(Square.class);
            shapeTypes.addElement(Line.class);
// Make some shapes:
            for(int i = 0; i < 10; i++)
                shapes.addElement(Shape.randomFactory());
// Set all the static colors to GREEN:
            for(int i = 0; i < 10; i++)
                ((Shape)shapes.elementAt(i)).setColor(Shape.GREEN);
// Save the state vector:
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CADState.out"));
            out.writeObject(shapeTypes);
            Line.serializeStaticState(out);
            out.writeObject(shapes);
        } else { // There's a command-line argument
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[0]));
// Read in the same order they were written:
            shapeTypes = (Vector)in.readObject();
            Line.deserializeStaticState(in);
            shapes = (Vector)in.readObject();
        }
// Display the shapes:
        System.out.println(shapes);
    }

}
