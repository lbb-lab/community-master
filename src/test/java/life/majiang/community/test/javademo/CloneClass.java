package life.majiang.community.test.javademo;

/**
 * @author: liu bin bin
 * @Date: 2021/3/9 17:07
 * @Description:
 */
public class CloneClass implements Cloneable{

    public int aInt;
    public Object clone(){
        CloneClass o = null;
       try{
          o = (CloneClass)super.clone();
        }catch(CloneNotSupportedException e){
           e.printStackTrace();
           }
       return o;
      }

    public Object noclone(){
        CloneClass o = null;
        try{
            o = (CloneClass)super.clone();
           System.out.println(o.hashCode()==(super.hashCode()));
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return o;
    }
}
