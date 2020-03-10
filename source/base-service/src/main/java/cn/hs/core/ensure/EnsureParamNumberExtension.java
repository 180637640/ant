package cn.hs.core.ensure;

/**
 * 断言判断
 * @author swt
 */
public class EnsureParamNumberExtension extends EnsureParam<Number> {
    private Double number;

    public EnsureParamNumberExtension(Number number) {
        super(number);
        if (number != null) {
            this.number = number.doubleValue();
        }
    }

    public EnsureParamNumberExtension isNull(String errorCode, String... args){
        if(number == null){
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 大于
     * @param comparedNumber
     * @param errorCode
     * @return
     */
    public EnsureParamNumberExtension isGt(Number comparedNumber, String errorCode, String... args) {
        if (number > comparedNumber.doubleValue()) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 不大于（小于等于）
     * @param comparedNumber
     * @param errorCode
     * @return
     */
    public EnsureParamNumberExtension isNotGt(Number comparedNumber, String errorCode, String... args) {
        if (number <= comparedNumber.doubleValue()) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 小于
     * @param comparedNumber
     * @param errorCode
     * @return
     */
    public EnsureParamNumberExtension isLt(Number comparedNumber, String errorCode, String... args) {
        if (number < comparedNumber.doubleValue()) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 不小于
     * @param comparedNumber
     * @param errorCode
     * @return
     */
    public EnsureParamNumberExtension isNotLt(Number comparedNumber, String errorCode, String... args) {
        if (number >= comparedNumber.doubleValue()) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 等于
     * @param comparedNumber
     * @param errorCode
     * @return
     */
    public EnsureParamNumberExtension isEqual(Number comparedNumber, String errorCode, String... args) {
        if (number.equals(comparedNumber)) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    /**
     * 不等于
     *
     * @param comparedNumber
     * @param errorCode
     * @return
     */
    public EnsureParamNumberExtension isNotEqual(Number comparedNumber, String errorCode, String... args) {
        if (number.equals(comparedNumber)) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }
}
