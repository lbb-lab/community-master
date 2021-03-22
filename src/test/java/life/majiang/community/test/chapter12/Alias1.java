package life.majiang.community.test.chapter12;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 9:33
 * @Description: “别名”意味着多个句柄都试图指向同一个对象，就象前面的例子展示的那样。若有人向那个对象里写入一  点什么东西，
 * 就会产生别名问题。若其他句柄的所有者不希望那个对象改变，恐怕就要失望了。这可用下面 这个简单的例子说明：
 *
 * 对下面这行：
 * Alias1 y = x; // Assign the handle
 * 它会新建一个Alias1 句柄，但不是把它分配给由new 创建的一个新鲜对象，而是分配给一个现有的句柄。所以句柄 x 的内容——
 * 即对象 x 指向的地址——被分配给y，所以无论 x 还是y 都与相同的对象连接起来。这样一来，一旦x 的i 在下述语句中增值：
 * x.i++;
 * y 的i 值也必然受到影响
 */
public class Alias1 {
    int i;
    Alias1(int ii) { i = ii; }
    public static void main(String[] args) {
        Alias1 x = new Alias1(7);
        Alias1 y = x; // Assign the handle
        System.out.println("x: " + x.i);
        System.out.println("y: " + y.i);
        System.out.println("Incrementing x");
        x.i++;
        System.out.println("x: " + x.i);
        System.out.println("y: " + y.i);
    }

}
