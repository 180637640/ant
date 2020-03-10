package cn.hs.core.ensure;


/**
 * 断言判断
 * @author swt
 */
public class EnsureParamEnumExtension extends EnsureParam<Enum> {
    private Enum anEnum;

    public EnsureParamEnumExtension(Enum anEnum) {
        super(anEnum);
        this.anEnum = anEnum;
    }

    /**
     * Enum 相等
     * @param comparedEnum
     * @param errorCode
     * @return
     */
    public EnsureParamEnumExtension isEqual(Enum comparedEnum, String errorCode, String... args) {
        if (anEnum == comparedEnum) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * Enum 不相等
     * @param comparedEnum
     * @param errorCode
     * @return
     */
    public EnsureParamEnumExtension isNotEqual(Enum comparedEnum, String errorCode, String... args){
        if(anEnum != comparedEnum){
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    public EnsureParamEnumExtension isNull(String errorCode, String... args) {
        if (anEnum == null) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }
}
