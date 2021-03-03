package life.majiang.community.utils;


import life.majiang.community.dto.ParamLogDTO;

/**
 * 打印参数日志实体本地线程类
 *
 * @author mengjiaxing
 * @since 2020/7/27
 */
public class ParamLogHolder {

    private static final ThreadLocal<ParamLogDTO> requestAttributesHolder = new ThreadLocal<>();
    private static final ThreadLocal<Long> startTimeHolder = new ThreadLocal<>();

    public static ParamLogDTO getParamLogDTO() {
        return requestAttributesHolder.get();
    }

    public static void setParamLogDTO(ParamLogDTO paramLogDTO) {
        requestAttributesHolder.set(paramLogDTO);
    }

    public static void removeParamLogDTO() {
        requestAttributesHolder.remove();
    }

    public static Long getStartTimeMs() {
        return startTimeHolder.get();
    }

    public static void setStartTimeMs(Long cosTimeMs) {
        startTimeHolder.set(cosTimeMs);
    }

    public static void removeStartTimeMs() {
        startTimeHolder.remove();
    }

    public static Long getCosTimeMs() {
        Long startTimeMs = getStartTimeMs();
        if (null != startTimeMs) {
            return System.currentTimeMillis() - startTimeMs;
        }
        return null;
    }
}
