package life.majiang.community.test.finallytest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 16:05
 * @Description:   即使违例不在当前的catch 从句集里捕获，finally 都会在违例控制机制转到更高级别搜索一个控制器之前得以执行。如下所示：
 * 若调用了break 和continue 语句，finally 语句也会得以执行。请注意，与作上标签的break 和continue 一道，finally 排除了 Java 对goto 跳转语句的需求。
 */
public class AlwaysFinally {
    public static void main(String[] args) {
        System.out.println("Entering first try block");
        try {
            System.out.println("Entering second try block");
            try {
                throw new Ex();
            } finally {
                System.out.println("finally in 2nd try block");
            }
        } catch(Ex e) {
            System.out.println("Caught Ex in first try block");
        } finally {
            System.out.println("finally in 1st try block");
        }
    }

}
