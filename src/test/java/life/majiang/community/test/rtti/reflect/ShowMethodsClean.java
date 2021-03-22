package life.majiang.community.test.rtti.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 1:25
 * @Description: ShowMethodsClean 方法非常接近前一个ShowMethods，只是它取得了Method 和Constructor 数组，并将它们转换成单个 String 数组。
 * 随后，每个这样的 String 对象都在 StripQualifiers.Strip()里“过”一遍，删除所有方法限定词。正如大家看到的那样，此时用到了StreamTokenizer
 * 和String 来完成这个工作。
 * 假如记不得一个类是否有一个特定的方法，而且不想在联机文档里逐步检查类结构，或者不知道那个类是否 能对某个对象（如 Color 对象）做某件事情，
 * 该工具便可节省大量编程时间。
 * 第17 章提供了这个程序的一个GUI 版本，可在自己写代码的时候运行它，以便快速查找需要的东西。
 *
 * 利用RTTI 可根据一个匿名的基础类句柄调查出类型信息。但正是由于这个原因，新手们极易误用它，因为有些时候多形性方法便足够了。
 * 对那些以前习惯程序化编程的人来说，极易将他们的程序组织成一系列switch 语句。他们可能用 RTTI 做到这一点，
 * 从而在代码开发和维护中损失多形性技术的重要价值。Java 的要求是让我们尽可能地采用多形性，只有在极特别的情况下才使用RTTI。
 * 但为了利用多形性，要求我们拥有对基础类定义的控制权，因为有些时候在程序范围之内，可能发现基础类
 * 并未包括我们想要的方法。若基础类来自一个库，或者由别的什么东西控制着，RTTI  便是一种很好的解决方案：可继承一个新类型，
 * 然后添加自己的额外方法。在代码的其他地方，可以侦测自己的特定类型，并调用  那个特殊的方法。这样做不会破坏多形性以及程序的扩展能力，
 * 因为新类型的添加不要求查找程序中的switch 语句。但在需要新特性的主体中添加新代码时，就必须用 RTTI 侦测自己特定的类型。
 * 从某个特定类的利益的角度出发，在基础类里加入一个特性后，可能意味着从那个基础类衍生的其他所有类
 * 都必须获得一些无意义的“鸡肋”。这使得接口变得含义模糊。若有人从那个基础类继承，且必须覆盖抽象   方法，这一现象便会使他们陷入困扰。
 * 比如现在用一个类结构来表示乐器（Instrument）。假定我们想清洁  管弦乐队中所有适当乐器的通气音栓（Spit Valve），
 * 此时的一个办法是在基础类Instrument 中置入一个ClearSpitValve()方法。但这样做会造成一个误区，因为它暗示着打击乐器和电子乐器中也有音栓。
 * 针对这  种情况，RTTI 提供了一个更合理的解决方案，可将方法置入特定的类中（此时是Wind，即“通气口”）—— 这样做是可行的。
 * 但事实上一种更合理的方案是将 prepareInstrument()置入基础类中。初学者刚开始时往往看不到这一点，一般会认定自己必须使用RTTI。
 * 最后，RTTI  有时能解决效率问题。若代码大量运用了多形性，但其中的一个对象在执行效率上很有问题，便可用RTTI 找出那个类型，
 * 然后写一段适当的代码，改进其效率。
 */
public class ShowMethodsClean {
    static final String usage =
            "usage: \n" + "ShowMethodsClean qualified.class.name\n" + "To show all methods in class or: \n"
                    + "ShowMethodsClean qualif.class.name word\n" + "To search for methods involving 'word'";

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println(usage);
            System.exit(0);
        }

        try {
            Class c = Class.forName(args[0]);
            Method[] m = c.getMethods();
            Constructor[] ctor = c.getConstructors();
// Convert to an array of cleaned Strings:
            String[] n = new String[m.length + ctor.length];
            for(int i = 0; i < m.length; i++) {
                String s = m[i].toString();
                n[i] = StripQualifiers.strip(s);
            }
            for(int i = 0; i < ctor.length; i++) {
                String s = ctor[i].toString();
                n[i + m.length] = StripQualifiers.strip(s);
            }
            if(args.length == 1)
                for (int i = 0; i < n.length; i++)
                    System.out.println(n[i]);
            else
                for (int i = 0; i < n.length; i++)
                    if(n[i].indexOf(args[1])!= -1)
                        System.out.println(n[i]);
        } catch (ClassNotFoundException e) {
            System.out.println("No such class: " + e);
        }
    }

}
