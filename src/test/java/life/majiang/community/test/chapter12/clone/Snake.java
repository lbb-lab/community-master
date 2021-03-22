package life.majiang.community.test.chapter12.clone;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 10:24
 * @Description: 调用Object.clone()时，实际发生的是什么事情呢？当我们在自己的类里覆盖 clone()时，什么东西对于super.clone() 来说是最关键的呢？
 * 根类中的  clone()方法负责建立正确的存储容量，并通过“按位复制”将二进制位从原始对象中复制到新对象的存储空间。也就是说，
 * 它并不只是预留存储空间以及复制一个对象—
 * —实际需要调查出欲复制之对象的准确大小，然后复制那个对象。由于所有这些工作都是在由根类定义之clone()方法的内部代码中进行的
 * （根类并不知道要从自己这里继承出去什么），所以大家或许已经猜到，这 个过程需要用RTTI  判断欲克隆的对象的实际大小。采取这种方式，
 * clone()方法便可建立起正确数量的存储空间，并对那个类型进行正确的按位复制。
 * 不管我们要做什么，克隆过程的第一个部分通常都应该是调用 super.clone() 。通过进行一次准确的复制，
 * 这样做可为后续的克隆进程建立起一个良好的基础。随后，可采取另一些必要的操作，以完成最终的克隆。   为确切了解其他操作是什么，
 * 首先要正确理解 Object.clone()为我们带来了什么。特别地，它会自动克隆所有句柄指向的目标吗？下面这个例子可完成这种形式的检测：
 *
 * 一条Snake（蛇）由数段构成，每一段的类型都是Snake。所以，这是一个一段段链接起来的列表。所有段都 是以循环方式创建的，
 * 每做好一段，都会使第一个构建器参数的值递减，直至最终为零。而为给每段赋予一   个独一无二的标记，第二个参数（一个Char）
 * 的值在每次循环构建器调用时都会递增。
 * increment()方法的作用是循环递增每个标记，使我们能看到发生的变化；而 toString 则循环打印出每个标记。输出如下：
 *
 * s = :a:b:c:d:e
 * s2 = :a:b:c:d:e
 * after s.increment, s2 = :a:c:d:e:f
 *
 * 这意味着只有第一段才是由Object.clone()复制的，所以此时进行的是一种“浅层复制”。若希望复制整条 蛇——即进行“深层复制”
 * ——必须在被覆盖的clone()里采取附加的操作。
 * 通常可在从一个能克隆的类里调用  super.clone() ，以确保所有基础类行动（包括Object.clone()）能够进行。
 * 随着是为对象内每个句柄都明确调用一个clone()；否则那些句柄会别名变成原始对象的句柄。构建器的调用也大致相同——
 * 首先构造基础类，然后是下一个衍生的构建器⋯⋯以此类推，直到位于最深层的衍生   构建器。区别在于 clone()并不是个构建器，
 * 所以没有办法实现自动克隆。为了克隆，必须由自己明确进行。
 */
public class Snake implements Cloneable{
    private Snake next;
    private char c;
    // Value of i == number of segments
    Snake(int i, char x) {
        c = x;
        if(--i > 0)
            next = new Snake(i, (char)(x + 1));
    }

    void increment() {
        c++;
        if(next != null)
            next.increment();
    }

    public String toString() {
        String s = ":" + c;
        if(next != null)
            s += next.toString();
        return s;
    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {}
        return o;
    }

    public static void main(String[] args) {
        Snake s = new Snake(5, 'a');
        System.out.println("s = " + s);
        Snake s2 = (Snake)s.clone();
        System.out.println("s2 = " + s2);
        s.increment();
        System.out.println("after s.increment, s2 = " + s2);
    }

}
