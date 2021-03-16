package life.majiang.community.test.hashtabletest;

import java.util.Hashtable;

/**
 * @author: liu bin bin
 * @Date: 2021/3/15 17:00
 * @Description: 注意这段代码使用了来自前一个例子的Prediction，所以 SpringDetector.java 必须首先编译，否则就会在试图编译SpringDetector2.java
 * 时得到一个编译期错误。Groundhog2.hashCode()将土拔鼠号码作为一个标识符返回（在这个例子中，程序员需要保证没有两个土拔鼠
 * 用同样的ID 号码并存）。为了返回一个独一无二的标识符，并不需要hashCode()，equals() 方法必须能够严格判断两个对象是否相等。
 * equals() 方法要进行两种检查：检查对象是否为null；若不为null ，则继续检查是否为Groundhog2 的一个实例（要用到instanceof 关键字，第 11 章会详加论述）。
 * 即使为了继续执行 equals()，它也应该是一个Groundhog2。正如大家看到的那样，这种比较建立在实际 ghNumber 的基础上。
 * 这一次一旦我们运行程序，就会看到它终于产生了正确的输出（许多Java 库的类都覆盖了hashcode() 和equals() 方法，以便与自己提供的内容适应）。
 */
public class SpringDetector2 {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        for(int i = 0; i < 10; i++)
            ht.put(new Groundhog2(i),new Prediction());
        System.out.println("ht = " + ht + "\n");
        System.out.println("Looking up prediction for groundhog #3:");
        Groundhog2 gh = new Groundhog2(3);
        if(ht.containsKey(gh))
            System.out.println((Prediction)ht.get(gh));
    }

}
