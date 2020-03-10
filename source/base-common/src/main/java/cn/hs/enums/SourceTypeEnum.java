package cn.hs.enums;

/**
 * @author ytl
 */
public enum  SourceTypeEnum {

    SECTION("1", "截面"),
    KEY_AREA("2", "重点区域"),
    CHOSEN_SHIP("3", "选船"),
    PATROL_SHIP("4", "巡船"),
    OTHER_SHIP("5", "其他");

    /** 代码
     * */
    private String code;
    /** 描述 */
    private String desc;

    SourceTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
