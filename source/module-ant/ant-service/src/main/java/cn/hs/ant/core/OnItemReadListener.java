package cn.hs.ant.core;

import cn.hs.ant.bean.MetaData;

import java.util.Map;

/**
 * 更新数据监听
 * @author swt
 */
public interface OnItemReadListener {

    /**
     * 读取
     * @param metaDataMap   元数据
     */
    void itemRead(Map<String, MetaData> metaDataMap);

}
