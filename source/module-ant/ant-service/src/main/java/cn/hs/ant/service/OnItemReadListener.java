package cn.hs.ant.service;

import java.util.List;

/**
 * 更新数据监听
 * @author swt
 */
public interface OnItemReadListener {

    /**
     * 读取
     * @param nameList      列名
     * @param typeList      数据类型
     * @param valueList     列值
     */
    void itemRead(List<String> nameList, List<Integer> typeList, List<Object> valueList);

}
