package life.majiang.community.test.finallytest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 16:02
 * @Description: 在这儿，sw.off() 已移至一个地方。无论发生什么事情，都肯定会运行它。
 * 即使违例不在当前的catch 从句集里捕获，finally 都会在违例控制机制转到更高级别搜索一个控制器之前得以执行。
 * 对比OnOffSwitch.java
 */
public class WithFinally {
    static Switch2 sw = new Switch2();
    public static void main(String[] args) {
        try {
            sw.on();
            // Code that can throw exceptions...
        } catch(NullPointerException e) {
            System.out.println("NullPointerException");
        } catch(IllegalArgumentException e) {
            System.out.println("IOException");
        } finally {
            sw.off();
        }
    }

}
