package life.majiang.community.constant;

/**
 * @author: liu bin bin
 * @Date: 2021/2/19 14:43
 * @Description:
 */
public enum ResultCode {
    SUCCESS("0000", "ok"),

    UN_AUTHORIZED("401", "Request Unauthorized"),
    NOT_FOUND("404", "404 Not Found"),
    MSG_NOT_READABLE("400", "Message Can't be Read"),
    METHOD_NOT_SUPPORTED("405", "Method Not Supported"),
    MEDIA_TYPE_NOT_SUPPORTED("415", "Media Type Not Supported"),
    REQ_REJECT("403", "Request Rejected"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
    PARAM_MISS("400", "Missing Required Parameter"),
    PARAM_TYPE_ERROR("400", "Parameter Type Mismatch"),
    PARAM_BIND_ERROR("400", "Parameter Binding Error"),
    PARAM_VALID_ERROR("400", "Parameter Validation Error"),
    TRANSFER_BEAN_FAILED("400", "transferBean failed"),
    // 以下为业务错误代码，至少4位数，避免与HTTP的错误号相同
    PARAM_OR_TYPE_IS_NULL("40001","校验参数或类型为空"),
    TYPE_ERROR("40002","校验类型有误"),
    USER_UNREGISTER("40003","用户未注册，请先注册！"),
    USER_NAME_OR_PASSWORD_IS_NULL("40004","用户名或密码输入为空"),
    USER_NAME_DUPLICATE("40005","用户名已注册"),
    USER_EMAIL_DUPLICATE("40006","邮箱已注册"),
    USER_PHONE_DUPLICATE("40007","手机号已注册"),
    REGISTER_INFO_SHORTAGE("40008","注册信息不完善"),
    PASSWORD_ERROR("40009","密码不正确")
    ;

    final String code;

    final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
