package cn.hs.core.ensure;

/**
 * 断言判断
 * @author swt
 */
public class EnsureParamIntegerExtension extends EnsureParam<Integer> {
    private Integer integer;

    public EnsureParamIntegerExtension(Integer integer) {
        super(integer);
        this.integer = integer;
    }

    public EnsureParamIntegerExtension isNull(String errorCode, String... args){
        if(integer == null){
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 大于
     * @param comparedInteger
     * @param errorCode
     * @return
     */
    public EnsureParamIntegerExtension isGt(Integer comparedInteger, String errorCode, String... args) {
        if (integer > comparedInteger) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 不大于（小于等于）
     * @param comparedInteger
     * @param errorCode
     * @return
     */
    public EnsureParamIntegerExtension isNotGt(Integer comparedInteger, String errorCode, String... args) {
        if (integer <= comparedInteger) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 小于
     * @param comparedInteger
     * @param errorCode
     * @return
     */
    public EnsureParamIntegerExtension isLt(Integer comparedInteger, String errorCode, String... args) {
        if (integer < comparedInteger) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 不小于
     * @param comparedInteger
     * @param errorCode
     * @return
     */
    public EnsureParamIntegerExtension isNotLt(Integer comparedInteger, String errorCode, String... args) {
        if (integer >= comparedInteger) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 等于
     * @param comparedInteger
     * @param errorCode
     * @return
     */
    public EnsureParamIntegerExtension isEqual(Integer comparedInteger, String errorCode, String... args) {
        if (integer.equals(comparedInteger)) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 不等于
     *
     * @param comparedInteger
     * @param errorCode
     * @return
     */
    public EnsureParamIntegerExtension isNotEqual(Integer comparedInteger, String errorCode, String... args) {
        if (!integer.equals(comparedInteger)) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }
}
