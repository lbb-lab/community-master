package life.majiang.community.test.rtti.petcount;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 0:32
 * @Description: 在这里，typenames（类型名）数组已被删除，改为从 Class 对象里获取类型名称。注意为此而额外做的工作：例如，类名不是Getbil，
 * 而是 c11.petcount2.Getbil，其中已包含了包的名字。也要注意系统是能够区分类和接口的。
 * 也可以看到，petTypes 的创建模块不需要用一个 try 块包围起来，因为它会在编译期得到检查，不会象Class.forName()那样“掷”出任何违例。
 * Pet 动态创建好以后，可以看到随机数字已得到了限制，位于 1 和petTypes.length 之间，而且不包括零。那是由于零代表的是Pet.class，
 * 而且一个普通的 Pet 对象可能不会有人感兴趣。然而，由于Pet.class 是petTypes 的一部分，所以所有Pet（宠物）都会算入计数中。
 */
public class PetCount2 {
    public static void main(String[] args) {
        Vector pets = new Vector();
        Class[] petTypes = {
// Class literals work in Java 1.1+ only:
                Pet.class, Dog.class, Pug.class, Cat.class, Rodent.class, Gerbil.class, Hamster.class,
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
            h.put(petTypes[i].toString(),
                    new Counter());
        for(int i = 0; i < pets.size(); i++) {
            Object o = pets.elementAt(i);
            if(o instanceof Pet)
                ((Counter)h.get("class life.majiang.community.test.rtti.petcount.Pet")).i++;
            if(o instanceof Dog)
                ((Counter)h.get("class life.majiang.community.test.rtti.petcount.Dog")).i++;
            if(o instanceof Pug)
                ((Counter)h.get("class life.majiang.community.test.rtti.petcount.Pug")).i++;
            if(o instanceof Cat)
                ((Counter)h.get("class life.majiang.community.test.rtti.petcount.Cat")).i++;
            if(o instanceof Rodent)
                ((Counter)h.get("class life.majiang.community.test.rtti.petcount.Rodent")).i++;
            if(o instanceof Gerbil)
                ((Counter)h.get("class life.majiang.community.test.rtti.petcount.Gerbil")).i++;
            if(o instanceof Hamster)
                ((Counter)h.get("class life.majiang.community.test.rtti.petcount.Hamster")).i++;
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
