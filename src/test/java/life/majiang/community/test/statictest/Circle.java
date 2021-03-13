package life.majiang.community.test.statictest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/13 22:10
 * @Description:
 */
public class Circle extends Shape{
    public static void main(String[] args){
        Circle circle = new Circle();
        Circle circle1 = new Circle();
        System.out.println("circle1 == circle2 ? "+ (circle.i == circle1.i));
    }
}
