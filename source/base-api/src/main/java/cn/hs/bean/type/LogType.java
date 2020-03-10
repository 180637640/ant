package cn.hs.bean.type;

/**
 * 日志类型
 */
public enum LogType {
    LOGIN("登录"),
    LOGOUT("注销"),
    DATA_SYNC("数据同步");

    private String result = "";

    LogType(String i){
        this.result = i;
    }

    @Override
    public String toString() {
        return result;
    }
}
