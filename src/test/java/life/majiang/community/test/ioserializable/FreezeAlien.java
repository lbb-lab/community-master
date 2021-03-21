package life.majiang.community.test.ioserializable;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 17:47
 * @Description:
 */
public class FreezeAlien {
    public static void main(String[] args) throws Exception {
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("file.x"));
        Alien zorcon = new Alien();
        out.writeObject(zorcon);
    }

}
