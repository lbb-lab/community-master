package life.majiang.community.test.rtti.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 1:16
 * @Description: Class 方法getMethods()和getConstructors()可以分别返回Method 和Constructor 的一个数组。每个类都提供了进一步的方法，
 * 可解析出它们所代表的方法的名字、参数以及返回值。但也可以象这样一样只使用toString()，生成一个含有完整方法签名的字串。
 * 代码剩余的部分只是用于提取命令行信息，判断特定的签   名是否与我们的目标字串相符（使用indexOf()），并打印出结果。
 * 这里便用到了“反射”技术，因为由Class.forName()产生的结果不能在编译期间获知，所以所有方法签名 信息都会在运行期间提取。
 * 若研究一下联机文档中关于“反射”（Reflection）的那部分文字，就会发现它 已提供了足够多的支持，可对一个编译期完全未知的对象
 * 进行实际的设置以及发出方法调用。同样地，这也  属于几乎完全不用我们操心的一个步骤——Java 自己会利用这种支持，所以程序设计环境
 * 能够控制 Java Beans——但它无论如何都是非常有趣的。
 * 一个有趣的试验是运行 java ShowMehods ShowMethods 。这样做可得到一个列表，其中包括一个 public 默认
 * 构建器，尽管我们在代码中看见并没有定义一个构建器。我们看到的是由编译器自动合成的那一个构建器。    如果随之将 ShowMethods 设为一个
 * 非 public 类（即换成“友好”类），合成的默认构建器便不会在输出结果中出现。合成的默认构建器会自动获得与类一样的访问权限。
 * ShowMethods 的输出仍然有些“不爽”。例如，下面是通过调用 java ShowMethods java.lang.String 得到
 * 的输出结果的一部分：
 *
 * public boolean java.lang.String.startsWith(java.lang.String,int)
 * public boolean java.lang.String.startsWith(java.lang.String)
 * public boolean
 * java.lang.String.endsWith(java.lang.String)
 *
 *
 */
public class ShowMethods {
    static final String usage = "usage: \n" + "ShowMethods qualified.class.name\n" +
            "To show all methods in class or: \n" + "ShowMethods qualified.class.name word\n"
            + "To search for methods involving 'word'";

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println(usage);
            System.exit(0);
        }

        try {
            Class c = Class.forName(args[0]);
            Method[] m = c.getMethods();
            Constructor[] ctor = c.getConstructors();
            if(args.length == 1) {
                for (int i = 0; i < m.length; i++)
                    System.out.println(m[i].toString());
                for (int i = 0; i < ctor.length; i++)
                    System.out.println(ctor[i].toString());
            }
            else {
                for (int i = 0; i < m.length; i++)
                    if(m[i].toString().indexOf(args[1])!= -1)
                        System.out.println(m[i].toString());
                for (int i = 0; i < ctor.length; i++)
                    if(ctor[i].toString().indexOf(args[1])!= -1)
                        System.out.println(ctor[i].toString());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("No such class: " + e);
        }
    }

}
