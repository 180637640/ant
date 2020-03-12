package cn.hs.ant.core;

import cn.hs.ant.core.adapter.ExtractAdapter;
import cn.hs.ant.core.adapter.LoadAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 转换服务
 * @author swt
 */
public class TransformService {
    private static Log log = LogFactory.getLog(TransformService.class);

    private ExtractAdapter extractAdapter;

    private LoadAdapter loadAdapter;

    private OnFinishListener onFinishListener;

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    public void setExtractAdapter(ExtractAdapter extractAdapter) {
        this.extractAdapter = extractAdapter;
    }

    public void setLoadAdapter(LoadAdapter loadAdapter) {
        this.loadAdapter = loadAdapter;
    }

    /**
     * 开始
     */
    public void start() {
        if(null == loadAdapter || null == extractAdapter) {
            log.error("适配器未设置");
            return;
        }
        // 抽取监听
        extractAdapter.setOnItemReadListener(loadAdapter);
        // 开始抽取
        extractAdapter.extract();

        if(null != onFinishListener) {
            onFinishListener.onFinish();
        }
    }

}
