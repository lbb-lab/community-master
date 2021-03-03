package life.majiang.community.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 打印参数日志实体
 *
 * @author mengjiaxing
 * @since 2020/7/27
 */
@Data
public class ParamLogDTO {
    private String url;
    private String ip;
    private String remoteHost;
    private String remoteAddr;
    private Object body;
    /**
     * 业务额外数据
     */
    private Map<String, Object> others = new HashMap<>();
    private long cosTimeMs;
    private Map<String, Object> params;
    private Map<String, String> headers;
    private Object resp;

    public ParamLogDTO() {
    }
}
