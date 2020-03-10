package cn.hs.bean.status;

/**
 * 启用禁用状态
 * @author swt
 */
public enum EnableDisableStatus {
    /** 启用 */
    ENABLE(1),
    /** 禁用 */
    DISABLE(2);

    private int result = 1;

    EnableDisableStatus(int i){
        this.result = i;
    }

    @Override
    public String toString() {
        return Integer.toString(result);
    }

    public Integer getValue() {
        return result;
    }

    public static EnableDisableStatus valueOf(Integer i) {
        return (ENABLE.getValue().equals(i)) ? ENABLE : DISABLE;
    }
}
