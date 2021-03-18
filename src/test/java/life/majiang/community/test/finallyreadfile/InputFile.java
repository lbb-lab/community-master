package life.majiang.community.test.finallyreadfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: liu bin bin
 * @Date: 2021/3/18 16:29
 * @Description:
 */
public class InputFile {
    private BufferedReader in;

    InputFile(String fname) throws Exception {
        try {
            in = new BufferedReader(new FileReader(fname));

            // Other code that might throw exceptions
        } catch (FileNotFoundException e) {
            System.out.println("Could not open " + fname);

            // Wasn't open, so don't close it throw e;
        }catch (Exception e) {

            // All other exceptions must close it
            try {
                in.close();
            } catch (Exception e2) {
                System.out.println("in.close() unsuccessful");
            }
            throw e;
        } finally {
            // Don't close it here!!!
            // finally 从句显然不是关闭文件的正确地方，因为这可能在每次构建器结束的时候关闭它。
            // 由于我们希望文件在InputFile 对象处于活动状态时一直保持打开状态，所以这样做并不恰当。
            //后面的调用程序用到这里的句柄
        }


    }

    String getLine() { String s;
        try {
            s = in.readLine();
        } catch(IOException e) {
            System.out.println("readLine() unsuccessful");
            s = "failed";
        }
        return s;
    }

    void cleanup() {
        try {
            in.close();
        } catch(IOException e2) {
            System.out.println("in.close() unsuccessful");
        }
    }

}
