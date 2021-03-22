package life.majiang.community.test.rtti.petcount;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 0:43
 * @Description: 2.    动态的 instanceof
 * Java 1.1 为Class 类添加了 isInstance 方法。利用它可以动态调用instanceof 运算符。而在Java 1.0 中，只能静态地调用它（就象前面指出的那样）。
 * 因此，所有那些烦人的instanceof 语句都可以从PetCount 例子中删去了。如下所示：
 *
 * 可以看到，Java 1.1 的isInstance()方法已取消了对instanceof 表达式的需要。此外，这也意味着一旦要求添加新类型宠物，
 * 只需简单地改变petTypes 数组即可；毋需改动程序剩余的部分（但在使用 instanceof 时却是必需的）。
 */
public class PetCount3 {
    public static void main(String[] args) {
        Vector pets = new Vector();
        Class[] petTypes = { Pet.class, Dog.class, Pug.class, Cat.class, Rodent.class, Gerbil.class, Hamster.class,
        };
        try {
            for(int i = 0; i < 15; i++) {
// Offset by one to eliminate Pet.class:
                int rnd = 1 + (int)(Math.random() * (petTypes.length - 1));
                pets.addElement(petTypes[rnd].newInstance());
            }
        } catch(InstantiationException e) {}
        catch(IllegalAccessException e) {}
        Hashtable h = new Hashtable();
        for(int i = 0; i < petTypes.length; i++)
            h.put(petTypes[i].toString(), new Counter());
        for(int i = 0; i < pets.size(); i++) {
            Object o = pets.elementAt(i);
// Using isInstance to eliminate individual
// instanceof expressions:
            for (int j = 0; j < petTypes.length; ++j)
                if (petTypes[j].isInstance(o)) {
                    String key = petTypes[j].toString();
                    ((Counter)h.get(key)).i++;
                }
        }
        for(int i = 0; i < pets.size(); i++)
            System.out.println(pets.elementAt(i).getClass().toString());
        Enumeration keys = h.keys();
        while(keys.hasMoreElements()) {
            String nm = (String)keys.nextElement();
            Counter cnt = (Counter)h.get(nm);
            System.out.println(nm.substring(nm.lastIndexOf('.') + 1) + " quantity: " + cnt.i);
        }
    }



}
