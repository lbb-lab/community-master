package life.majiang.community.test.rtti.petcount;

import java.util.Hashtable;
import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 0:23
 * @Description: 迄今为止，我们已知的 RTTI 形式包括：
 * (1)	经典造型，如"(Shape)"，它用 RTTI 确保造型的正确性，并在遇到一个失败的造型后产生一个ClassCastException 违例。
 * (2)	代表对象类型的Class 对象。可查询Class 对象，获取有用的运行期资料。
 *
 * 在C++中，经典的"(Shape)"造型并不执行RTTI。它只是简单地告诉编译器将对象当作新类型处理。而Java  要执行类型检查，
 * 这通常叫作“类型安全”的下溯造型。之所以叫“下溯造型”，是由于类分层结构的历史   排列方式造成的。若将一个Circle（圆）
 * 造型到一个 Shape（几何形状），就叫做上溯造型，因为圆只是几何形状的一个子集。反之，若将Shape 造型至 Circle，就叫做下溯造型。
 * 然而，尽管我们明确知道Circle 也是一个Shape，所以编译器能够自动上溯造型，但却不能保证一个 Shape 肯定是一个 Circle。因此，
 * 编译器不允许自动下溯造型，除非明确指定一次这样的造型。
 * RTTI 在Java 中存在三种形式。关键字 instanceof 告诉我们对象是不是一个特定类型的实例（Instance 即“实例”）。它会返回一个布尔值，
 * 以便以问题的形式使用，就象下面这样：
 * if(x instanceof Dog) ((Dog)x).bark();
 * 将x 造型至一个 Dog 前，上面的 if 语句会检查对象x 是否从属于 Dog 类。进行造型前，如果没有其他信息可以告诉自己对象的类型，
 * 那么 instanceof 的使用是非常重要的——否则会得到一个ClassCastException 违例。
 * 我们最一般的做法是查找一种类型（比如要变成紫色的三角形），但下面这个程序却演示了如何用instanceof 标记出所有对象
 *
 * 在Java 1.0 中，对 instanceof 有一个比较小的限制：只可将其与一个已命名的类型比较，不能同Class 对象作对比。在上述例子中，
 * 大家可能觉得将所有那些instanceof 表达式写出来是件很麻烦的事情。实际情况正是这样。但在Java 1.0 中，没有办法让这一工作自动进行
 * ——不能创建Class 的一个 Vector，再将其与之比较。大家最终会意识到，如编写了数量众多的 instanceof 表达式，整个设计都可能出现问题。
 * 当然，这个例子只是一个构想——最好在每个类型里添加一个static 数据成员，然后在构建器中令其增值， 以便跟踪计数。编写程序时，
 * 大家可能想象自己拥有类的源码控制权，能够自由改动它。但由于实际情况并   非总是这样，所以 RTTI 显得特别方便。
 */
public class PetCount {
    static String[] typenames = {
            "Pet", "Dog", "Pug", "Cat",
            "Rodent", "Gerbil", "Hamster",
    };
    public static void main(String[] args) { Vector pets = new Vector();
        try {
            Class[] petTypes = { Class.forName("life.majiang.community.test.rtti.petcount.Dog"),
                    Class.forName("life.majiang.community.test.rtti.petcount.Pug"),
                    Class.forName("life.majiang.community.test.rtti.petcount.Cat"),
                    Class.forName("life.majiang.community.test.rtti.petcount.Rodent"),
                    Class.forName("life.majiang.community.test.rtti.petcount.Gerbil"),
                    Class.forName("life.majiang.community.test.rtti.petcount.Hamster"),

            };

            for(int i = 0; i < 15; i++)
                pets.addElement(petTypes[ (int)(Math.random()*petTypes.length)].newInstance());
        } catch(InstantiationException e) {}
        catch(IllegalAccessException e) {}
        catch(ClassNotFoundException e) {}
        Hashtable h = new Hashtable();
        for(int i = 0; i < typenames.length; i++)
            h.put(typenames[i], new Counter());
        for(int i = 0; i < pets.size(); i++) {
            Object o = pets.elementAt(i);
            if(o instanceof Pet) ((Counter)h.get("Pet")).i++;
            if(o instanceof Dog) ((Counter)h.get("Dog")).i++;
            if(o instanceof Pug) ((Counter)h.get("Pug")).i++;
            if(o instanceof Cat) ((Counter)h.get("Cat")).i++;
            if(o instanceof Rodent) ((Counter)h.get("Rodent")).i++;
            if(o instanceof Gerbil) ((Counter)h.get("Gerbil")).i++;
            if(o instanceof Hamster) ((Counter)h.get("Hamster")).i++;
        }
        for(int i = 0; i < pets.size(); i++)
            System.out.println(pets.elementAt(i).getClass().toString());
        for(int i = 0; i < typenames.length; i++)
            System.out.println( typenames[i] + " quantity: " + ((Counter)h.get(typenames[i])).i);
    }

}
