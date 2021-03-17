package life.majiang.community.test.extendssynthetic;

/**
 * @author: liu bin bin
 * @Date: 2021/3/13 15:40
 * @Description:
 */
public class PlaceSetting extends Custom {
    Spoon sp;
    Fork  frk;
    Knife kn;
    DinnerPlate pl;
    PlaceSetting(int i){
        super(i+1);
        sp = new Spoon(i+2);
        frk = new Fork(i+3);
        kn = new Knife(i+4);
        pl = new DinnerPlate(i+5);
        System.out.println("PlaceSetting constructor");
    }

    public static void main(String[] args){
        PlaceSetting x = new PlaceSetting(9);
    }
}
