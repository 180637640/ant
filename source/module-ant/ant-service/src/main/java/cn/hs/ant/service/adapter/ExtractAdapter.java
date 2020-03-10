package cn.hs.ant.service.adapter;

import cn.hs.ant.service.DataTerminal;
import cn.hs.ant.service.OnItemReadListener;

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
     * @param dataTerminal
     */
    void setDataTerminal(DataTerminal dataTerminal);

    /**
     * 添加监听
     * @param onItemReadListener    监听接口
     */
    void setOnItemReadListener(OnItemReadListener onItemReadListener);

}
