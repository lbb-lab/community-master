package life.majiang.community.test.chapter14_thread.threadgroup;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 23:49
 * @Description: 在main()中，我们创建了几个ThreadGroup（线程组），每个都位于不同的“叶”上：x 没有参数，只有它的名字（一个String），
 * 所以会自动进入“system”（系统）线程组；y 位于x 下方，而 z 位于y 下方。注意初始化是按照文字顺序进行的，所以代码合法。
 * 有两个线程创建之后进入了不同的线程组。其中，TestThread1 没有一个 run()方法，但有一个f()，用于通知线程以及打印出一些东西，
 * 以便我们知道它已被调用。而TestThread2 属于TestThread1 的一个子类，它的run()非常详尽，要做许多事情。首先，它获得当前线程所在的线程组，
 * 然后利用getParent()在继承树中 向上移动两级（这样做是有道理的，因为我想把TestThread2  在分级结构中向下移动两级）。随后，我们调用方法
 * activeCount() ，查询这个线程组以及所有子线程组内有多少个线程，从而创建由指向Thread 的句柄构成的一个数组。enumerate()方法将指向所有
 * 这些线程的句柄置入数组 gAll 里。然后在整个数组里遍历， 为每个线程都调用   f()方法，同时修改优先级。这样一来，位于一个“叶子”线程组
 * 里的线程就修改了位于 父线程组的线程。
 * 调试方法list()打印出与一个线程组有关的所有信息，把它们作为标准输出。在我们对线程组的行为进行调 查的时候，这样做是相当有好处的
 * 下面是程序的输出：
 *
 * java.lang.ThreadGroup[name=x,maxpri=10] Thread[one,5,x]
 * java.lang.ThreadGroup[name=y,maxpri=10]
 * java.lang.ThreadGroup[name=z,maxpri=10] Thread[two,5,z]
 * one f()
 * two f() java.lang.ThreadGroup[name=x,maxpri=10]
 * Thread[one,1,x]
 * java.lang.ThreadGroup[name=y,maxpri=10] java.lang.ThreadGroup[name=z,maxpri=10]
 * Thread[two,1,z]
 *
 * list()不仅打印出 ThreadGroup 或者Thread 的类名，也打印出了线程组的名字以及它的最高优先级。对于线程，则打印出它们的名字，
 * 并接上线程优先级以及所属的线程组。注意list()会对线程和线程组进行缩排处  理，指出它们是未缩排的线程组的“子”。
 * 大家可看到 f()是由TestThread2 的run()方法调用的，所以很明显，组内的所有线程都是相当脆弱的。然
 * 而，我们只能访问那些从自己的system  线程组树分支出来的线程，而且或许这就是所谓“安全”的意思。我们不能访问其他任何人的系统线程树。
 */
public class TestAccess {
    public static void main(String[] args) {
        ThreadGroup x = new ThreadGroup("x"),
                y = new ThreadGroup(x, "y"),
                z = new ThreadGroup(y, "z");
        Thread one = new TestThread1(x, "one"),
                two = new TestThread2(z, "two");
    }

}
