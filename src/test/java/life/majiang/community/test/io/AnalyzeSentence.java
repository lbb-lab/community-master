package life.majiang.community.test.io;

import java.util.StringTokenizer;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 10:05
 * @Description: 尽管并不必要IO 库的一部分，但StringTokenizer 提供了与 StreamTokenizer 极相似的功能，所以在这里一并讲述。
 * StringTokenizer  的作用是每次返回字串内的一个记号。这些记号是一些由制表站、空格以及新行分隔的连续字符。因此，字串“Where is my cat?”
 * 的记号分别是“Where”、“is”、“my”和“cat?”。与StreamTokenizer 类似，我们可以指示 StringTokenizer 按照我们的愿望分割输入。
 * 但对于StringTokenizer，却需要向构建器传递另一个参数，即我们想使用的分隔字串。通常，如果想进行更复杂的 操作，应使用StreamTokenizer。
 * 可用nextToken()向StringTokenizer  对象请求字串内的下一个记号。该方法要么返回一个记号，要么返回一个空字串（表示没有记号剩下）。
 * 作为一个例子，下述程序将执行一个有限的句法分析，查询键短语序列，了解句子暗示的是快乐亦或悲伤的含义。
 *
 * 对于准备分析的每个字串，我们进入一个 while 循环，并将记号从那个字串中取出。请注意第一个 if 语句， 假如记号既不是“I”，也不是“Are”，
 * 就会执行 continue（返回循环起点，再一次开始）。这意味着除非发现一个“I”或者“Are”，才会真正得到记号。大家可能想用==代替equals() 方法，
 * 但那样做会出现不正常的表现，因为==比较的是句柄值，而 equals() 比较的是内容。
 * analyze()方法剩余部分的逻辑是搜索“I am sad”（我很忧伤、“I am nothappy”（我不快乐）或者“Are you  sad?”（你悲伤吗？）这样的句法格式。
 * 若没有break 语句，这方面的代码甚至可能更加散乱。大家应注意对一个典型的解析器来说，通常都有这些记号的一个表格，并能在读取新记号的时候用
 * 一小段代码在表 格内移动。无论如何，只应将 StringTokenizer 看作StreamTokenizer 一种简单而且特殊的简化形式。然而，如果有一个字串需要进行记号处理，
 * 而且StringTokenizer 的功能实在有限，那么应该做的全部事情就是用StringBufferInputStream 将其转换到一个数据流里，
 * 再用它创建一个功能更强大的StreamTokenizer。
 */
public class AnalyzeSentence {
    public static void main(String[] args) {
        analyze("I am happy about this");
        analyze("I am not happy about this");
        analyze("I am not! I am happy");
        analyze("I am sad about this");
        analyze("I am not sad about this");
        analyze("I am not! I am sad");
        analyze("Are you happy about this?");
        analyze("Are you sad about this?");
        analyze("It's you! I am happy");
        analyze("It's you! I am sad");
    }

    static StringTokenizer st;

    static void analyze(String s) {
        prt("\nnew sentence >> " + s);
        boolean sad = false;
        st = new StringTokenizer(s);
        while (st.hasMoreTokens()) {
            String token = next();

            // Look until you find one of the
            // two starting tokens:
            if(!token.equals("I") && !token.equals("Are"))
                continue; // Top of while loop
            if(token.equals("I")) {
                String tk2 = next();
                if(!tk2.equals("am")) // Must be after I
                    break; // Out of while loop
                else {
                    String tk3 = next();
                    if(tk3.equals("sad")) {
                        sad = true;
                        break; // Out of while loop
                    }
                    if (tk3.equals("not")) {
                        String tk4 = next();
                        if(tk4.equals("sad"))
                            break; // Leave sad false
                        if(tk4.equals("happy")) {
                            sad = true;
                            break;
                        }
                    }
                }
            }
            if(token.equals("Are")) {
                String tk2 = next();
                if(!tk2.equals("you"))
                    break; // Must be after Are
                String tk3 = next();
                if(tk3.equals("sad"))
                    sad = true;
                break; // Out of while loop
            }
        }
        if(sad) prt("Sad detected");
    }

    static String next() {
        if(st.hasMoreTokens()) {
            String s = st.nextToken();
            prt(s);
            return s;
        }
        else
            return "";
    }

    static void prt(String s) {
        System.out.println(s);
    }



}
