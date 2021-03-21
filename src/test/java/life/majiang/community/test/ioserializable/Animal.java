package life.majiang.community.test.ioserializable;

import java.io.Serializable;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 19:22
 * @Description:
 */
public class Animal implements Serializable {
    String name;
    House preferredHouse;
    Animal(String nm, House h) {
        name = nm;
        preferredHouse = h;
    }
    public String toString() {
        return name + "[" + super.toString() + "], " + preferredHouse + "\n";
    }

}
