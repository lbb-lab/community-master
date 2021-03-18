package life.majiang.community.test.finallytest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 15:50
 * @Description:    无论这里是否发生异常，try 后面的finally语句都会执行
 *
 * 在没有“垃圾收集”以及“自动调用破坏器”机制的一种语言中（注释⑤），finally  显得特别重要，
 * 因为程序员可用它担保内存的正确释放——无论在 try 块内部发生了什么状况。但Java 提供了垃圾收集机制，
 * 所以内存的释放几乎绝对不会成为问题。另外，它也没有构建器可供调用。既然如此，Java 里何时才会用到finally 呢？
 */
public class FinallyWorks {
    static int count = 0;
    public static void main(String[] args) {

        while(true) {
            try {
                // post-increment is zero first time:
                if(count++ == 0)
                    throw new Exception();
                System.out.println("No exception");
            } catch(Exception e) {
                System.out.println("Exception thrown");
            } finally {
                System.out.println("in finally clause");
                if(count == 2) break; // out of "while"
            }
        }
    }

}
