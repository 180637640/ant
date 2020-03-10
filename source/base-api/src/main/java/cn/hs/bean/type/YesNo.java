package cn.hs.bean.type;

/**
 * 登录状态
 */
public enum YesNo {
    /** 是 */
    YES(1),
    /** 否 */
    NO(2);

    private int result = 1;

    YesNo(int i){
        this.result = i;
    }

    @Override
    public String toString() {
        return Integer.toString(result);
    }

    public Integer getValue() {
        return result;
    }

    public static YesNo valueOf(Integer i) {
        return (YES.getValue().equals(i)) ? YES : NO;
    }
}
