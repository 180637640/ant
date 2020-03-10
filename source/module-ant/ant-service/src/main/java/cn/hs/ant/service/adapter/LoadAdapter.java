package cn.hs.ant.service.adapter;

import cn.hs.ant.service.DataTerminal;
import cn.hs.ant.service.OnItemReadListener;

/**
 * 加载适配器
 * @author swt
 */
public interface LoadAdapter extends OnItemReadListener {

    /**
     * 设置数据源
     * @param dataTerminal
     */
    void setDataTerminal(DataTerminal dataTerminal);

}
