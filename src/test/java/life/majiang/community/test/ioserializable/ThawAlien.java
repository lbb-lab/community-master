package life.majiang.community.test.ioserializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 17:47
 * @Description: 该程序能打开文件，并成功读取mystery  对象中的内容。然而，一旦尝试查找与对象有关的任何资料——这要求Alien
 * 的Class 对象——Java 虚拟机（JVM）便找不到 Alien.class（除非它正好在类路径内，而本例理应相反）。这样就会得到一个名叫
 * ClassNotFoundException 的违例（同样地，若非能够校验Alien 存在的证据，否则它等于消失）。
 * 恢复了一个序列化的对象后，如果想对其做更多的事情，必须保证JVM 能在本地类路径或者因特网的其他什么地方找到相关的.class 文件。
 */
public class ThawAlien {
    public static void main(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("file.x"));
        Object mystery = in.readObject();
        System.out.println(mystery.getClass().toString());
    }


}
