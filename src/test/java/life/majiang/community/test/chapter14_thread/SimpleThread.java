package life.majiang.community.test.chapter14_thread;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 23:10
 * @Description: run()方法几乎肯定含有某种形式的循环——它们会一直持续到线程不再需要为止。因此，我们必须规定特定 的条件，
 * 以便中断并退出这个循环（或者在上述的例子中，简单地从 run()返回即可）。run()通常采用一种无限循环的形式。也就是说，
 * 通过阻止外部发出对线程的 stop()或者destroy() 调用，它会永远运行下去
 * （直到程序完成）。
 * 在main()中，可看到创建并运行了大量线程。Thread 包含了一个特殊的方法，叫作 start()，它的作用是对线程进行特殊的初始化，
 * 然后调用 run()。所以整个步骤包括：调用构建器来构建对象，然后用 start()配置线程，再调用run()。如果不调用 start()——如果适当的话，
 * 可在构建器那样做——线程便永远不会启动
 *
 * 可注意到这个例子中到处都调用了 sleep()，然而输出结果指出每个线程都获得了属于自己的那一部分CPU 执行时间。从中可以看出，
 * 尽管sleep()依赖一个线程的存在来执行，但却与允许或禁止线程无关。它只不 过是另一个不同的方法而已。
 * 亦可看出线程并不是按它们创建时的顺序运行的。事实上，CPU 处理一个现有线程集的顺序是不确定的—— 除非我们亲自介入，
 * 并用Thread 的setPriority() 方法调整它们的优先级。
 * main()创建Thread 对象时，它并未捕获任何一个对象的句柄。普通对象对于垃圾收集来说是一种“公平竞赛”，但线程却并非如此。
 * 每个线程都会“注册”自己，所以某处实际存在着对它的一个引用。这样一来， 垃圾收集器便只好对它“瞠目以对”了。
 */
public class SimpleThread extends Thread {
    private int countDown = 5;
    private int threadNumber;
    private static int threadCount = 0;
    public SimpleThread() {
        threadNumber = ++threadCount;
        System.out.println("Making " + threadNumber);
    }
    public void run() {
        while(true) {
            System.out.println("Thread " + threadNumber + "(" + countDown + ")");
            if(--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++)
            new SimpleThread().start();
        System.out.println("All Threads Started");
    }

}
