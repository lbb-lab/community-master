package life.majiang.community.test.chapter12.clone.check;

/**
 * @author: liu bin bin
 * @Date: 2021/3/22 12:50
 * @Description:
 * 总之，如果希望一个类能够克隆，那么：
 * (1)	实现Cloneable 接口
 * (2)	覆盖clone()
 * (3)	在自己的clone()中调用 super.clone()
 * (4)	在自己的clone()中捕获违例
 * 这一系列步骤能达到最理想的效果。
 */
public class CheckCloneable {
    static Ordinary tryToClone(Ordinary ord) {
        String id = ord.getClass().getName();
        Ordinary x = null;
        if(ord instanceof Cloneable) {
            try {
                System.out.println("Attempting " + id);
                x = (Ordinary)((IsCloneable)ord).clone();
                System.out.println("Cloned " + id);
            } catch(CloneNotSupportedException e) {
                System.out.println("Could not clone " + id);
            }
        }
        return x;
    }

    public static void main(String[] args) {
        // Upcasting:
        Ordinary[] ord = {
                new IsCloneable(), new WrongClone(), new NoMore(),
                new TryMore(), new BackOn(), new ReallyNoMore(),
        };
        Ordinary x = new Ordinary();
        // This won't compile, since clone() is
        // protected in Object:
        //! x = (Ordinary)x.clone();
        // tryToClone() checks first to see if
        // a class implements Cloneable:
        for(int i = 0; i < ord.length; i++)
            tryToClone(ord[i]);
    }

}
