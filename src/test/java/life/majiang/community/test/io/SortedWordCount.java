package life.majiang.community.test.io;

import life.majiang.community.test.file.StrSortVector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author: liu bin bin
 * @Date: 2021/3/19 9:26
 * @Description: 尽管StreamTokenizer 并不是从 InputStream 或OutputStream 衍生的，但它只随同InputStream 工作，所以十分恰当地包括在库的 IO 部分中。
 * StreamTokenizer 类用于将任何 InputStream 分割为一系列“记号”（Token）。这些记号实际是一些断续的文本块，中间用我们选择的任何东西分隔。
 * 例如，我们的记号可以是单词，中间用空白（空格）以及标点符号分隔。下面是一个简单的程序，用于计算各个单词在文本文件中重复出现的次数：
 *
 *
 * 最好将结果按排序格式输出，但由于Java 1.0 和Java 1.1 都没有提供任何排序方法，所以必须由自己动手。这个目标可用一个 StrSortVector 方便地达成
 * （创建于第 8 章，属于那一章创建的软件包的一部分。记住本书所有子目录的起始目录都必须位于类路径中，否则程序将不能正确地编译）。
 * 为打开文件，使用了一个FileInputStream。而且为了将文件转换成单词，从 FileInputStream 中创建了一个StreamTokenizer。在StreamTokenizer  中，
 * 存在一个默认的分隔符列表，我们可用一系列方法加入更多的分隔符。在这里，我们用ordinaryChar()指出“该字符没有特别重要的意义”，
 * 所以解析器不会把它当作 自己创建的任何单词的一部分。例如，st.ordinaryChar('.')表示小数点不会成为解析出来的单词的一部    分。
 * 在与Java 配套提供的联机文档中，可以找到更多的相关信息。在countWords()中，每次从数据流中取出一个记号，而ttype 信息的作用是判断对每个记号
 * 采取什么操作— —因为记号可能代表一个行尾、一个数字、一个字串或者一个字符。找到一个记号后，会查询Hashtable  counts，核实其中是否
 * 已经以“键”（Key）的形式包含了一个记号。若答案是肯定的，对应的Counter（计数器）对象就会增值，指出已找到该单词的另一个实例。若答案为
 * 否，则新建一个Counter——因为 Counter 构建器会将它的值初始化为1，正是我们计算单词数量时的要求。SortedWordCount 并不属于 Hashtable（散列表）
 * 的一种类型，所以它不会继承。它执行的一种特定类型的操作，所以尽管keys()和values() 方法都必须重新揭示出来，但仍不表示应使用那个继承，
 * 因为大量Hashtable  方法在这里都是不适当的。除此以外，对于另一些方法来说（比如getCounter()——用于获得一 个特定字串的计数器；
 * 又如sortedKeys()——用于产生一个枚举），它们最终都改变了 SortedWordCount 接口的形式。在main()内，我们用 SortedWordCount 打开和计算文件中
 * 的单词数量——总共只用了两行代码。随后，我们为一个排好序的键（单词）列表提取出一个枚举。并用它获得每个键以及相关的 Count（计数）。
 * 注意必须调用cleanup()，否则文件不能正常关闭。采用了 StreamTokenizer 的第二个例子将在第17 章提供
 */
public class SortedWordCount {
    private FileInputStream file;
    private StreamTokenizer st;
    private Hashtable counts = new Hashtable();

    SortedWordCount(String filename) throws FileNotFoundException {
        try {
            file = new FileInputStream(filename);
            st = new StreamTokenizer(file);
            st.ordinaryChar('.');
            st.ordinaryChar('-');
        } catch(FileNotFoundException e) {
            System.out.println("Could not open " + filename);
            throw e;
        }
    }

    void cleanup() { try {
        file.close();
    } catch(IOException e) {
        System.out.println("file.close() unsuccessful");
    }
    }

    void countWords() {
        try {
            while(st.nextToken() != StreamTokenizer.TT_EOF) {
                String s;
                switch(st.ttype) {
                    case StreamTokenizer.TT_EOL:
                        s = new String("EOL");
                        break;
                    case StreamTokenizer.TT_NUMBER:
                        s = Double.toString(st.nval);
                        break;
                    case StreamTokenizer.TT_WORD:
                        s = st.sval; // Already a String break;
                    default: // single character in ttype
                        s = String.valueOf((char)st.ttype);
                }

                if(counts.containsKey(s))
                    ((Counter)counts.get(s)).increment();
                else
                    counts.put(s, new Counter());
            }
        } catch(IOException e) {
            System.out.println("st.nextToken() unsuccessful");
        }
    }

    Enumeration values() { return counts.elements();
    }

    Enumeration keys() { return counts.keys(); }

    Counter getCounter(String s) {
        return (Counter)counts.get(s);
    }

    Enumeration sortedKeys() {
        Enumeration e = counts.keys();
        StrSortVector sv = new StrSortVector();
        while(e.hasMoreElements())
            sv.addElement((String)e.nextElement());
// This call forces a sort:
        return sv.elements();
    }

    public static void main(String[] args) {
        try {
            SortedWordCount wc = new SortedWordCount("pom.xml");
            wc.countWords();
            Enumeration keys = wc.sortedKeys();
            while(keys.hasMoreElements()) {
                String key = (String)keys.nextElement();
                System.out.println(key + ": " + wc.getCounter(key).read());
            }
            wc.cleanup();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
