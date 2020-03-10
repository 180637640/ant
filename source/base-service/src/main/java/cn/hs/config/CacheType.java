package cn.hs.config;

/**
 * 缓存类型
 * @author swt
 */
public enum CacheType {

    /**
     * 搜索
     */
    SEARCH("SEARCH"),

    /**
     * 实体
     */
    OBJECT("OBJECT");

    private final String value;

    CacheType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
