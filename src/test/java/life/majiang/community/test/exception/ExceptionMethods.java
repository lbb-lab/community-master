package life.majiang.community.test.exception;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 11:01
 * @Description:
 */
public class ExceptionMethods {
    public static void main(String[] args) {
        try {
        throw new Exception("Here's my Exception");
    } catch(Exception e) {
        System.out.println("Caught Exception");
        System.out.println("e.getMessage(): " + e.getMessage());
        System.out.println("e.toString(): " + e.toString());
        System.out.println("e.printStackTrace():");
        e.printStackTrace();
    }
    }



}
