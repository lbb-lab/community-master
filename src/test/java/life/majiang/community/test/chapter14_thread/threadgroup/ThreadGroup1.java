package life.majiang.community.test.chapter14_thread.threadgroup;

/**
 * @author: liu bin bin
 * @Date: 2021/3/23 0:16
 * @Description:
 * 所有程序都至少有一个线程在运行，而且 main()采取的第一项行动便是调用Thread 的一个 static（静态） 方法，名为 currentThread()。从这个线程开始，线程组将被创建，而且会为结果调用 list()。输出如下：
 *
 * (1)	ThreadGroup[name=system,maxpri=10] Thread[main,5,system]
 *
 * 我们可以看到，主线程组的名字是 system，而主线程的名字是 main，而且它从属于system 线程组。第二个练习显示出 system 组的最高优先级可以减少，而且main 线程可以增大自己的优先级：
 *
 * (2)	ThreadGroup[name=system,maxpri=9] Thread[main,6,system]
 *
 * 第三个练习创建一个新的线程组，名为g1；它自动从属于system 线程组，因为并没有明确指定它的归属关系。我们在 g1 内部放置了一个新线程，名为 A。随后，我们试着将这个组的最大优先级设到最高的级别，并将A 的优先级也设到最高一级。结果如下：
 *
 * (3)	ThreadGroup[name=g1,maxpri=9] Thread[A,9,g1]
 *
 * 可以看出，不可能将线程组的最大优先级设为高于它的父线程组。
 * 第四个练习将g1 的最大优先级降低两级，然后试着把它升至 Thread.MAX_PRIORITY。结果如下：
 *
 * (4)	ThreadGroup[name=g1,maxpri=8] Thread[A,9,g1]
 *
 * 同样可以看出，提高最大优先级的企图是失败的。我们只能降低一个线程组的最大优先级，而不能提高它。  此外，注意线程A 的优先级并未改变，而且它现在高于线程组的最大优先级。也就是说，线程组最大优先级的变化并不能对现有线程造成影响。
 * 第五个练习试着将一个新线程设为最大优先级。如下所示：
 *
 * (5)	ThreadGroup[name=g1,maxpri=8] Thread[A,9,g1] Thread[B,8,g1]
 *
 * 因此，新线程不能变到比最大线程组优先级还要高的一级。
 * 这个程序的默认线程优先级是 6；若新建一个线程，那就是它的默认优先级，而且不会发生变化，除非对优先级进行了特别的处理。练习六将把线程组的最大优先级降至默认线程优先级以下，看看在这种情况下新建 一个线程会发生什么事情：
 *
 * (6)	ThreadGroup[name=g1,maxpri=3]
 *
 * Thread[B,8,g1] Thread[C,6,g1]
 *
 * 尽管线程组现在的最大优先级是3，但仍然用默认优先级 6 来创建新线程。所以，线程组的最大优先级不会影响默认优先级（事实上，似乎没有办法可以设置新线程的默认优先级）。
 * 改变了优先级后，接下来试试将其降低一级，结果如下：
 *
 * (7)	ThreadGroup[name=g1,maxpri=3] Thread[A,9,g1] Thread[B,8,g1] Thread[C,3,g1]
 *
 * 因此，只有在试图改变优先级的时候，才会强迫遵守线程组最大优先级的限制。
 * 我们在(8)和(9)中进行了类似的试验。在这里，我们创建了一个新的线程组，名为g2，将其作为 g1 的一个子组，并改变了它的最大优先级。大家可以看到，g2 的优先级无论如何都不可能高于 g1：
 *
 * (8)	ThreadGroup[name=g2,maxpri=3]
 * (9)	ThreadGroup[name=g2,maxpri=3]
 *
 * 也要注意在 g2 创建的时候，它会被自动设为 g1 的线程组最大优先级。
 * 经过所有这些实验以后，整个线程组和线程系统都会被打印出来，如下所示：
 *
 * (10)ThreadGroup[name=system,maxpri=9] Thread[main,6,system] ThreadGroup[name=g1,maxpri=3]
 * Thread[A,9,g1]
 * Thread[B,8,g1] Thread[C,3,g1]
 * ThreadGroup[name=g2,maxpri=3]
 * Thread[0,6,g2] Thread[1,6,g2] Thread[2,6,g2] Thread[3,6,g2] Thread[4,6,g2]
 */
public class ThreadGroup1 {
    public static void main(String[] args) {
// Get the system thread & print its Info:
        ThreadGroup sys = Thread.currentThread().getThreadGroup();
        sys.list(); // (1)
// Reduce the system thread group priority:
        sys.setMaxPriority(Thread.MAX_PRIORITY - 1);
// Increase the main thread priority:
        Thread curr = Thread.currentThread();
        curr.setPriority(curr.getPriority() + 1);
        sys.list(); // (2)
// Attempt to set a new group to the max:
        ThreadGroup g1 = new ThreadGroup("g1");
        g1.setMaxPriority(Thread.MAX_PRIORITY);
// Attempt to set a new thread to the max:
        Thread t = new Thread(g1, "A");
        t.setPriority(Thread.MAX_PRIORITY);
        g1.list(); // (3)
// Reduce g1's max priority, then attempt
// to increase it:
        g1.setMaxPriority(Thread.MAX_PRIORITY - 2);
        g1.setMaxPriority(Thread.MAX_PRIORITY);
        g1.list(); // (4)
// Attempt to set a new thread to the max:
        t = new Thread(g1, "B");
        t.setPriority(Thread.MAX_PRIORITY);
        g1.list(); // (5)
// Lower the max priority below the default
// thread priority:
        g1.setMaxPriority(Thread.MIN_PRIORITY + 2);
// Look at a new thread's priority before
// and after changing it:
        t = new Thread(g1, "C");
        g1.list(); // (6)
        t.setPriority(t.getPriority() -1);
        g1.list(); // (7)
// Make g2 a child Threadgroup of g1 and
// try to increase its priority:
        ThreadGroup g2 = new ThreadGroup(g1, "g2");
        g2.list(); // (8)
        g2.setMaxPriority(Thread.MAX_PRIORITY);
        g2.list(); // (9)
// Add a bunch of new threads to g2:
        for (int i = 0; i < 5; i++)
            new Thread(g2, Integer.toString(i));
// Show information about all threadgroups
// and threads:
        sys.list(); // (10)
        System.out.println("Starting all threads:");
        Thread[] all = new Thread[sys.activeCount()];
        sys.enumerate(all);
        for(int i = 0; i < all.length; i++)
            if(!all[i].isAlive())
                all[i].start();
// Suspends & Stops all threads in
// this group and its subgroups:
        System.out.println("All threads started");
        sys.suspend(); // Deprecated in Java 1.2
// Never gets here...
        System.out.println("All threads suspended");
        sys.stop(); // Deprecated in Java 1.2
        System.out.println("All threads stopped");
    }

}
