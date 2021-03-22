package life.majiang.community.test.chapter14_thread;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 22:21
 * @Description: 在这个程序中，AWT 和程序片代码都应是大家熟悉的，第 13 章对此已有很详细的交待。go()方法正是程序全心全意服务的对待：
 * 将当前的 count（计数）值置入TextField（文本字段）t，然后使count 增值。
 * go()内的部分无限循环是调用sleep()。sleep()必须同一个 Thread（线程）对象关联到一起，而且似乎每个应用程序都有部分线
 * 程同它关联（事实上，Java  本身就是建立在线程基础上的，肯定有一些线程会伴随我们写的应用一起运行）。所以无论我们是否明
 * 确使用了线程，都可利用 Thread.currentThread()产生由程序使用的当前线程，然后为那个线程调用sleep()。注意，Thread.currentThread()
 * 是Thread 类的一个静态方 法。
 * 注意sleep()可能“掷”出一个 InterruptException （中断违例）——尽管产生这样的违例被认为是中止线程的一种“恶意”手段，而且应该尽可能
 * 地杜绝这一做法。再次提醒大家，违例是为异常情况而产生的，而    不是为了正常的控制流。在这里包含了对一个“睡眠”线程的中断，以支持未来的一种语言特性。
 * 一旦按下start 按钮，就会调用go()。研究一下go()，你可能会很自然地（就象我一样）认为它该支持多线程，因为它会进入“睡眠”状态。
 * 也就是说，尽管方法本身“睡着”了，CPU 仍然应该忙于监视其他按钮“按下”事件。但有一个问题，那就是go()是永远不会返回的，因为它被设计成
 * 一个无限循环。这意味着actionPerformed()根本不会返回。由于在第一个按键以后便陷入  actionPerformed()中，所以程序不能再对其他任何事件进
 * 行控制（如果想出来，必须以某种方式“杀死”进程——最简便的方式就是在控制台窗口按Ctrl＋C 键）。
 * 这里最基本的问题是go()需要继续执行自己的操作，而与此同时，它也需要返回，以便 actionPerformed()
 * 能够完成，而且用户界面也能继续响应用户的操作。但对象go()这样的传统方法来说，它却不能在继续的同 时将控制权返回给程序的其他部分。
 * 这听起来似乎是一件不可能做到的事情，就象CPU 必须同时位于两个地方一样，但线程可以解决一切。“线程模型”（以及Java 中的编程支持）
 * 是一种程序编写规范，可在单独一个程序里实现几个操作的同时进行。根据这一机制，CPU  可为每个线程都分配自己的一部分时间。每个线程 都
 * “感觉”自己好象拥有整个 CPU，但CPU 的计算时间实际却是在所有线程间分摊的。
 * 线程机制多少降低了一些计算效率，但无论程序的设计，资源的均衡，还是用户操作的方便性，都从中获得
 *
 * 了巨大的利益。综合考虑，这一机制是非常有价值的。当然，如果本来就安装了多块 CPU，那么操作系统能够自行决定为不同的CPU 分配哪些线程，程
 * 序的总体运行速度也会变得更快（所有这些都要求操作系统以及应用程序的支持）。多线程和多任务是充分发挥多处理机系统能力的一种最有效的方式。
 *
 */
public class Counter1 extends Applet {
    private int count = 0;
    private Button onOff = new Button("Toggle"),
            start = new Button("Start");
    private TextField t = new TextField(10);
    private boolean runFlag = true;
    public void init() { add(t);
        start.addActionListener(new StartL());
        add(start);
        onOff.addActionListener(new OnOffL());
        add(onOff);
    }

    public void go() {
        while (true) {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e){}
            if(runFlag)
                t.setText(Integer.toString(count++));

        }
    }

    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            go();
        }
    }

    class OnOffL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }

    public static void main(String[] args) {
        Counter1 applet = new Counter1();
        Frame aFrame = new Frame("Counter1");
        aFrame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
        aFrame.add(applet, BorderLayout.CENTER);
        aFrame.setSize(300,200);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }

}
