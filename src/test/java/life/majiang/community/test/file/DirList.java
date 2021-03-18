package life.majiang.community.test.file;

import java.io.File;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 21:51
 * @Description: 回调的目的是在代码的行为上提供更大的灵活性。
 * 通过DirFilter，我们看出尽管一个“接口”只包含了一系列方法，但并不局限于只能写那些方法（但是， 至少必须提供一个接口内所有方法的定义。
 * 在这种情况下，DirFilter 构建器也会创建）。accept() 方法必须接纳一个 File 对象，用它指示用于寻找一个特定文件的目录；
 * 并接纳一个String，其中包含了要寻找之文件的名字。可决定使用或忽略这两个参数之一，但有时至少要使用文件名。记住list()方
 * 法准备为目录对象中的每个文件名调用accept()，核实哪个应包含在内——具体由 accept() 返回的“布尔” 结果决定。
 */
public class DirList {

    public static void main(String[] args) {
        try {
            File path = new File(".");
            String[] list;
            if(args.length == 0)
                list = path.list();
            else
                list = path.list(new DirFilter(args[0]));

            for(int i = 0; i < list.length; i++)
                System.out.println(list[i]);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
