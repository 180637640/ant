package cn.hs.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class JSONUtil {
    /**
     * 根据JSONArray String获取到List
     *
     * @param <T>
     * @param <T>
     * @param jArrayStr
     * @return
     */
    public static <T> List<T> getListByArray(String jArrayStr, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(jArrayStr, type);
    }

    /**
     * 根据List获取到对应的JSONArray
     *
     * @param list
     * @return
     */
    public static String getJSONArrayByList(List<?> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    /**
     * 根据JSONArray String获取到List
     *
     * @param memberName
     * @param json
     * @return
     */
    public static <T> List<T> getListByMemberName(String memberName, String json, Type type) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        //再转JsonArray 加上数据头
        JsonArray jsonArray = jsonObject.getAsJsonArray(memberName);
        Gson gson = new Gson();
        ArrayList<T> beanList = new ArrayList<>();
        //循环遍历
        for (JsonElement element : jsonArray) {
            //通过反射 得到UserBean.class
            T bean = gson.fromJson(element, type);
            beanList.add(bean);
        }
        return beanList;
    }

}
