package life.majiang.community.test.stacktest;

import java.util.Stack;

/**
 * @author: liu bin bin
 * @Date: 2021/3/15 12:35
 * @Description:
 */
public class Stacks {
    static String[] months = {
            "January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December" };
    public static void main(String[] args) {
        Stack stk = new Stack();
        for(int i = 0; i < months.length; i++)
            stk.push(months[i] + " ");
        System.out.println("stk = " + stk);

        // Treating a stack as a Vector:
        stk.addElement("The last line");
        System.out.println("element 5 = " + stk.elementAt(5));
        System.out.println("popping elements:");
        while(!stk.empty())
            System.out.println(stk.pop());
    }
//months 数组的每一行都通过 push()继承进入堆栈，稍后用 pop()从堆栈的顶部将其取出。要声明的一点是， Vector 操作亦可针对 Stack 对象进行。
// 这可能是由继承的特质决定的——Stack“属于”一种 Vector。因此，能对Vector 进行的操作亦可针对Stack 进行，例如 elementAt()方法

}
