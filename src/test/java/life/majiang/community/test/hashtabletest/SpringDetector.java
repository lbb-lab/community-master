package life.majiang.community.test.hashtabletest;

import java.util.Hashtable;

/**
 * @author: liu bin bin
 * @Date: 2021/3/15 16:51
 * @Description: 在main()中，用 Groundhog 以及与它们相关的 Prediction 填充一个散列表。散列表被打印出来，以便我们看到它们确实已被填充。随后，用标识号码为 3 的一个 Groundhog 查找与Groundhog #3 对应的预报。
 * 看起来似乎非常简单，但实际是不可行的。问题在于Groundhog 是从通用的 Object 根类继承的（若当初未指定基础类，则所有类最终都是从Object 继承的）。事实上是用 Object 的hashCode()方法生成每个对象的散
 *
 * 列码，而且默认情况下只使用它的对象的地址。所以，Groundhog(3)的第一个实例并不会产生与Groundhog(3)第二个实例相等的散列码，而我们用第二个实例进行检索。
 * 大家或许认为此时要做的全部事情就是正确地覆盖 hashCode()。但这样做依然行不能，除非再做另一件事情：覆盖也属于Object 一部分的 equals()。当散列表试图判断我们的键是否等于表内的某个键时，就会用到这个方法。
 * 同样地，默认的 Object.equals()只是简单地比较对象地址，所以一个 Groundhog(3)并不等于另一个 Groundhog(3)。
 * 因此，为了在散列表中将自己的类作为键使用，必须同时覆盖  hashCode()和equals()，就象SpringDetector2 展示的那样：
 */
public class SpringDetector {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        for(int i = 0; i < 10; i++)
            ht.put(new Groundhog(i), new Prediction());
        System.out.println("ht = " + ht + "\n");
        System.out.println("Looking up prediction for groundhog #3:");
        Groundhog gh = new Groundhog(3);
        if(ht.containsKey(gh))
            System.out.println((Prediction)ht.get(gh));
    }
}


