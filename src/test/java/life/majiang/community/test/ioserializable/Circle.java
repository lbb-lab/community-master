package life.majiang.community.test.ioserializable;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 19:49
 * @Description:
 */
public class Circle extends Shape {
    private static int color = RED;

    public Circle(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }
    public void setColor(int newColor) {
        color = newColor;
    }
    public int getColor() { return color;
    }

}
