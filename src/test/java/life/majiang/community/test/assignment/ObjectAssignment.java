package life.majiang.community.test.assignment;

/**
 * @author: liu bin bin
 * @Date: 2021/3/11 14:24
 * @Description:
 */
public class ObjectAssignment {

    public static void main(String[] args){
        TestClass n1 = new TestClass();
        TestClass n2 = new TestClass();
        n1.anInt = 27;
        n2.anInt = 16;
        n1=n2;
        n2.anInt=23;
        System.out.println("N1:"+n1.anInt+" N2:"+n2.anInt);  //这个时候n1和n2的值是一样的，因为对象相等相当于把n2的对象给了n1，n1和n2共享一个对象
                                                             //最开始的n1的创建的对象被丢失了，会被垃圾回收掉
    }




}
