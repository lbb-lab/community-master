package life.majiang.community.test.rtti;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 0:56
 * @Description: 从中可以看出，class FancyToy 相当复杂，因为它从Toy 中继承，并实现了 HasBatteries，Waterproof 以及ShootsThings 的接口。
 * 在 main()中创建了一个 Class 句柄，并用位于相应 try 块内的 forName()初始化成FancyToy。
 * Class.getInterfaces 方法会返回 Class 对象的一个数组，用于表示包含在 Class 对象内的接口。
 * 若有一个Class 对象，也可以用getSuperclass()查询该对象的直接基础类是什么。当然，这种做会返回一个Class 句柄，可用它作进一步的查询。
 * 这意味着在运行期的时候，完全有机会调查到对象的完整层次结构。
 * 若从表面看，Class 的newInstance() 方法似乎是克隆（clone()）一个对象的另一种手段。但两者是有区别
 * 的。利用newInstance() ，我们可在没有现成对象供“克隆”的情况下新建一个对象。就象上面的程序演示的那样，当时没有 Toy 对象，
 * 只有 cy——即y 的Class 对象的一个句柄。利用它可以实现“虚拟构建器”。换言之，我们表达：“尽管我不知道你的准确类型是什么，
 * 但请你无论如何都正确地创建自己。”在上述例   子中，cy 只是一个Class 句柄，编译期间并不知道进一步的类型信息。一旦新建了一个实例后，
 * 可以得到Object 句柄。但那个句柄指向一个Toy 对象。当然，如果要将除Object 能够接收的其他任何消息发出去， 首先必须进行一些调查研究，
 * 再进行造型。除此以外，用 newInstance() 创建的类必须有一个默认构建器。没有办法用 newInstance() 创建拥有非默认构建器的对象，所以在Java 1.0 中
 * 可能存在一些限制。然而， Java 1.1 的“反射”API（下一节讨论）却允许我们动态地使用类里的任何构建器。
 * 程序中的最后一个方法是printInfo()，它取得一个Class 句柄，通过 getName()获得它的名字，并用interface()调查它是不是一个接口。
 *
 * 所以利用Class 对象，我们几乎能将一个对象的祖宗十八代都调查出来。
 */
public class ToyTest {
    public static void main(String[] args) {
        Class c = null;
//        try {
//            c = Class.forName("FancyToy");    //这里是为null，为什么？
//        } catch(ClassNotFoundException e) {}
        c = FancyToy.class;
        printInfo(c);
        Class[] faces = c.getInterfaces();
        for(int i = 0; i < faces.length; i++)
            printInfo(faces[i]);
        Class cy = c.getSuperclass();
        Object o = null;
        try {
// Requires default constructor:
            o = cy.newInstance(); // (*1*) //这里为什么是null？
        } catch(InstantiationException e) {}
        catch(IllegalAccessException e) {}

        printInfo(o.getClass());
    }
    static void printInfo(Class cc) {
        System.out.println("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
    }

}
