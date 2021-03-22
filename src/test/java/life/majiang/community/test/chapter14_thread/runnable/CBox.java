package life.majiang.community.test.chapter14_thread.runnable;

import java.awt.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/23 0:27
 * @Description: ColorBoxes 是一个典型的应用（程序），有一个构建器用于设置 GUI。这个构建器采用 int grid 的一个参数，用它设置GridLayout（网格布局），
 * 使每一维里都有一个grid 单元。随后，它添加适当数量的 CBox 对象，用它们填充网格，并为每一个都传递 pause 值。在 main()中，我们可看到如何对
 * pause 和grid 的默认值进行修改（如果用命令行参数传递）。
 * CBox 是进行正式工作的地方。它是从Canvas 继承的，并实现了 Runnable 接口，使每个 Canvas 也能是一个Thread。记住在实现Runnable 的时候，
 * 并没有实际产生一个 Thread 对象，只是一个拥有 run()方法的类。因此，我们必须明确地创建一个Thread 对象，并将 Runnable 对象传递给构建器，
 * 随后调用 start()（在构建器里进行）。在 CBox 里，这个线程的名字叫作 t。
 * 请留意数组 colors，它对 Color 类中的所有颜色进行了列举（枚举）。它在newColor()中用于产生一种随机
 * 选择的颜色。当前的单元（格）颜色是cColor。
 * paint()则相当简单——只是将颜色设为cColor，然后用那种颜色填充整张画布（Canvas）。
 * 在run()中，我们看到一个无限循环，它将 cColor 设为一种随机颜色，然后调用 repaint()把它显示出来。随后，对线程执行 sleep()，使其“休眠”由命令
 * 行指定的时间长度。
 * 由于这种设计方案非常灵活，而且线程处理同每个 Canvas 元素都紧密结合在一起，所以在理论上可以生成任意多的线程（但在实际应用中，这要受到 JVM
 * 能够从容对付的线程数量的限制）。
 * 这个程序也为我们提供了一个有趣的评测基准，因为它揭示了不同JVM 机制在速度上造成的戏剧性的差异。
 */
public class CBox extends Canvas implements Runnable {
    private Thread t;
    private int pause;
    private static final Color[] colors = { Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow
    };
    private Color cColor = newColor();
    private static final Color newColor() {
        return colors[
                (int)(Math.random() * colors.length)
                ];
    }
    public void paint(Graphics	g) {
        g.setColor(cColor);
        Dimension s = getSize();
        g.fillRect(0, 0, s.width, s.height);
    }
    public CBox(int pause) {
        this.pause = pause;
        t = new Thread(this);
        t.start();
    }
    public void run() {
        while(true) {
            cColor = newColor();
        repaint();
        try {
            t.sleep(pause);
        } catch(InterruptedException e) {}
    }
    }
}


