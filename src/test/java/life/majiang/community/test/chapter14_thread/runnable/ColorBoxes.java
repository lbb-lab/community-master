package life.majiang.community.test.chapter14_thread.runnable;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author: liu bin bin
 * @Date: 2021/3/23 0:29
 * @Description:
 */
public class ColorBoxes extends Frame {
    public ColorBoxes(int pause, int grid) {
        setTitle("ColorBoxes");
        setLayout(new GridLayout(grid, grid));
        for (int i = 0; i < grid * grid; i++)
            add(new CBox(pause));
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        int pause = 50;
        int grid = 8;
        if(args.length > 0)
            pause = Integer.parseInt(args[0]);
        if(args.length > 1)
            grid = Integer.parseInt(args[1]);
        Frame f = new ColorBoxes(pause, grid);
        f.setSize(500, 400);
        f.setVisible(true);
    }

}
