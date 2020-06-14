package com.simpleblog.vueblog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author hxd
 * @data 2019/11/3
 */
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 反序列化字符串为指定的类类型
     *
     * @param content   需要反序列化的字符串
     * @param valueType 返回实例的类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T readValue(String content, Class<T> valueType) throws IOException {
        return MAPPER.readValue(content, valueType);
    }

    /**
     * 将对象实例序列号为 json 字符串
     *
     * @param object 需要序列化的对象实例
     * @return
     * @throws JsonProcessingException
     */
    public static String writeValueAsString(Object object) throws JsonProcessingException {
        return MAPPER.writeValueAsString(object);
    }

    /**
     * 将对象实例序列化为 json 字符串，如果序列化异常则返回 null
     *
     * @param object 需要序列化的对象实例
     * @return
     */
    public static String toString(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
