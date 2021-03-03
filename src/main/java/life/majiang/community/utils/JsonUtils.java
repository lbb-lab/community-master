package life.majiang.community.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: liu bin bin
 * @Date: 2021/2/20 16:54
 * @Description:
 */
public class JsonUtils {
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final Gson ATTR_GSON_INSTANCE = new GsonBuilder().create();

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    public static String toJson(Object newObj) {
        return ATTR_GSON_INSTANCE.toJson(newObj);
    }

    /**
     * 字符串 json 转 bean
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * 使用方式:AttributeUtil.parseJson(jsonStr, ResumeAttr.class);
     * ResumeAttr.class = 需要转换的类型.
     */
    public static <T> T parseJson(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            json = "{}";
        }
        return ATTR_GSON_INSTANCE.fromJson(json, clazz);
    }

    /**
     * 字符串 json 转嵌套 bean
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * 使用方式:AttributeUtil.parseJsonToList(jsonStr,new TypeToken<List<ResumWorkExpAttr>>() {}.getType())
     * ResumWorkExpAttr = 需要转出的bean 类型
     */
    public static <T> T parseJsonToList(String json, Type clazz) {
        if (StringUtils.isBlank(json)) {
            json = "{}";
        }
        return ATTR_GSON_INSTANCE.fromJson(json, clazz);
    }

    /**
     * json字符串转成Map
     *
     * @param str
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map jsonStrToMap(String str) {
        if (str == null || str.trim().length() == 0) {
            return new HashMap();
        }
        return ATTR_GSON_INSTANCE.fromJson(str, Map.class);
    }

    /**
     * json字符串转List
     * @param str
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List jsonStrToList(String str, Class clazz) {
        if (str == null || str.trim().length() == 0) {
            return new ArrayList();
        }
        List<Map> list = fromJson(str, List.class);
        List calssList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (Map map : list) {
                calssList.add(fromJson(toJson(map), clazz));
            }
        }
        return calssList;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return (T) fromJson(json, clazz, null);
    }

    public static <T> T fromJson(String json, Class<T> clazz, String datePattern) {
        if (StringUtil.isEmpty(json)) {
            return null;
        }
        GsonBuilder builder = new GsonBuilder();
        if (StringUtil.isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        Gson gson = builder.create();
        try {
            return (T) gson.fromJson(json, clazz);
        }
        catch (Exception ex) {
            logger.info(json + " 无法转换为 " + clazz.getName() + " 对象!");
            return null;
        }
    }
}
