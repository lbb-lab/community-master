package life.majiang.community.test.ioserializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 19:50
 * @Description:
 */
public class Line extends Shape {
    private static int color = RED;

    public static void serializeStaticState(ObjectOutputStream os) throws IOException {
        os.writeInt(color);
    }

    public static void deserializeStaticState(ObjectInputStream os) throws IOException {
        color = os.readInt();
    }

    public Line(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    public void setColor(int newColor) {
        color = newColor;
    }
    public int getColor() {
        return color;
    }

}
