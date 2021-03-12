package life.majiang.community.test.shiftoperation;

/**
 * @author: liu bin bin
 * @Date: 2021/3/11 15:32
 * @Description:
 */
public class shiftOperation {
    public static void main(String[] args){
        int i = -1;
        i >>>= 10;
        System.out.println(i);
        long l = -1;
        l>>>=10;
        System.out.println(l);
        short s = -1;
        s >>>= 10;
        System.out.println(s);
        byte b = 10;
        System.out.println(b);
    }

}
