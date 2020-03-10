package cn.hs.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * @author yaotailin
 * @date 2019/12/3 10:58
 * 多边行工具
 */
public class DrawPolygonUtil<T> {
    private static Gson gson = new Gson();

    /**
     * 绘制json字符串转为对象
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static  <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    /**
     * 多边形对象转为json字符串
     *
     * @param src
     * @return
     */
    public static String toJson(Object src) throws JsonSyntaxException {
        return gson.toJson(src);
    }
    /**
     * 绘制json字符串转为对象
     *
     * @param json
     * @param type 数据类型 example: new TypeToken<List<Person>>(){}.getType())
     * @return
     */
    public static  <T> T fromJson(String json, Type type) throws JsonSyntaxException {
        return gson.fromJson(json, type);
    }

}

