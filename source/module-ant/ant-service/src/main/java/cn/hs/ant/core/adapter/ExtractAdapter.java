package cn.hs.ant.core.adapter;

import cn.hs.ant.core.endpoint.Endpoint;
import cn.hs.ant.core.OnItemReadListener;

/**
 * 抽取适配器
 * @author swt
 */
public interface ExtractAdapter {

    /**
     * 抽取
     */
    void extract();

    /**
     * 设置数据源
     * @param endpoint
     */
    void setEndpoint(Endpoint endpoint);

    /**
     * 添加监听
     * @param onItemReadListener    监听接口
     */
    void setOnItemReadListener(OnItemReadListener onItemReadListener);

}
