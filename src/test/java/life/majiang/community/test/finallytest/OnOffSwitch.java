package life.majiang.community.test.finallytest;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 15:57
 * @Description:  除将内存设回原始状态以外，若要设置另一些东西，finally 就是必需的。例如，我们有时需要打开一个文件或者建立一个网络连接，
 * 或者在屏幕上画一些东西，甚至设置外部世界的一个开关，等等。如下例所示：
 * 这里的目标是保证 main()完成时开关处于关闭状态，所以将 sw.off()置于try 块以及每个违例控制器的末尾。
 * 但产生的一个违例有可能不是在这里捕获的，这便会错过 sw.off()。
 * 然而，利用finally，我们可以将来自try 块的关闭代码只置于一个地方：
 * 详见：WithFinally.java代码
 */
public class OnOffSwitch {
    static Switch sw = new Switch();
    public static void main(String[] args) {
        try {
            sw.on();
            // Code that can throw exceptions...
            sw.off();
        } catch(NullPointerException e) {
            System.out.println("NullPointerException");
            sw.off();
        } catch(IllegalArgumentException e) {
            System.out.println("IOException");
            sw.off();
        }
    }



}
