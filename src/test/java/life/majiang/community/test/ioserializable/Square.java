package life.majiang.community.test.ioserializable;

/**
 * @author: liu bin bin
 * @Date: 2021/3/21 19:50
 * @Description:
 */
public class Square extends Shape{
    private static int color;
    public Square(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
        color = RED;
    }
    public void setColor(int newColor) { color = newColor;
    }
    public int getColor() { return color;
    }

}
