package life.majiang.community.test.chapter14_thread.runnable;

import java.awt.*;

/**
 * @author: liu bin bin
 * @Date: 2021/3/23 0:38
 * @Description:
 */
public class CBox2 extends Canvas {
    private static final Color[] colors = { Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow
    };
    private Color cColor = newColor();
    private static final Color newColor() {
        return colors[
                (int)(Math.random() * colors.length)
                ];
    }
    void nextColor() {
        cColor = newColor();
        repaint();
    }
    public void paint(Graphics	g) {
        g.setColor(cColor);
        Dimension s = getSize();
        g.fillRect(0, 0, s.width, s.height);
    }

}
