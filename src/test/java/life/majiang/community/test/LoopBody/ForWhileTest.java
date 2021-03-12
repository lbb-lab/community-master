package life.majiang.community.test.LoopBody;

/**
 * @author: liu bin bin
 * @Date: 2021/3/12 10:16
 * @Description:
 */
public class ForWhileTest {
    public static void main(String[] args){
        outer:
        for(int i = 0; i < 10; i++){
            inner:
            for(int j = 0; j < 10; j++){
                if (j == 7){
                    break outer;
                }
                if(j == 5){
                    continue inner;
                }
                System.out.print("("+i+","+j+") ");
            }
            System.out.println();
        }
    }


}
