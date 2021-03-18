package life.majiang.community.test.synchronizedtest;

import java.util.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 10:36
 * @Description:   在这儿，大家只需注意到 Collections 类提供了对整个容器进行自动同步的一种途径。它的语法与“不可修改”的方法是类似的：
 * 在这种情况下，我们通过适当的“同步”方法直接传递新容器；这样做可避免不慎暴露出未同步的版本。
 * 新集合也提供了能防止多个进程同时修改一个容器内容的机制。若在一个容器里反复，同时另一些进程介入，并在那个容器中插入、删除或修改一个对象，
 * 便会面临发生冲突的危险。我们可能已传递了那个对象，可能它位位于我们前面，可能容器的大小在我们调用size()后已发生了收缩——我们面临各种各样可能的危险。
 * 针对这个问题，新的集合库集成了一套解决的机制，能查出除我们的进程自己需要负责的之外的、对容器的其他任何修改。若探测到有其他方面也准备修改容器，
 * 便会立即产生一个ConcurrentModificationException （并发修改违例）。我们将这一机制称为“立即失败”——它并不用更复杂的算法在“以后”侦测问题，而是“立即”产生违例。
 */
public class Synchronization {
    public static void main(String[] args) {
        Collection c = Collections.synchronizedCollection( new ArrayList());
        List list = Collections.synchronizedList( new ArrayList());
        Set s = Collections.synchronizedSet(new HashSet());
        Map m = Collections.synchronizedMap( new HashMap());
    }

}
