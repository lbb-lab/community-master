package life.majiang.community.test.chapter12.deepclone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 11:42
 * @Description:   添加克隆能力之前，编译器会阻止我们的克隆尝试。一旦在Scientist 里添加了克隆能力，那么Scientist 以及它的所有“后裔”都可以克隆。
 */
public class HorrorFlick {
    public static void main(String[] args) {
        Person p = new Person();
        Hero h = new Hero();
        Scientist s = new Scientist();
        MadScientist m = new MadScientist();

        // p = (Person)p.clone(); // Compile error
        // h = (Hero)h.clone(); // Compile error
        s = (Scientist)s.clone();
        m = (MadScientist)m.clone();
    }

}
