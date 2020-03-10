package cn.hs.bean.type;

/**
 * 登录状态
 */
public enum LoginResult {
    /** 登录成功 */
    SUCCESS(1),
    /** 不存在 */
    NONE(2),
    /** 锁定、禁用 */
    LOCKED(3),
    /** 登录失败 */
    FAIL(4);

    private int result = 1;

    LoginResult(int i){
        this.result = i;
    }

    @Override
    public String toString() {
        return Integer.toString(result);
    }

    public Integer getValue() {
        return result;
    }
}
