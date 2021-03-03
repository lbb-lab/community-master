package life.majiang.community.constant;

import lombok.Data;


/**
 * @author: liu bin bin
 * @Date: 2021/2/19 14:33
 * @Description:
 */
@Data
public class R<T> {

    String msg;

    String code;

    T data;

    public R(){ }

    public R(ResultCode resultCode){
        this(resultCode.getMsg(),resultCode.getCode(),null);
    }

    public R(ResultCode resultCode,T data){
        this(resultCode.msg,resultCode.code,data);
    }

    public static <T> R success(T data){
        return new R(ResultCode.SUCCESS,data);
    }

    public static <T> R success(){
        return new R(ResultCode.SUCCESS);
    }

    public static R fail(ResultCode resultCode){
        return new R(resultCode.msg,resultCode.msg,null);
    }

    R(String msg, String code, T data){
        this.msg = msg;
        this.code = code;
        this.data = data;
    }


}
