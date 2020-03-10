package cn.hs.core.ensure;


/**
 * 断言判断
 * @author swt
 */
public class EnsureParamBooleanExtension extends EnsureParam<Boolean> {
    private Boolean condition;

    public EnsureParamBooleanExtension(Boolean condition) {
        super(condition);
        this.condition = condition;
    }

    public EnsureParamBooleanExtension isFalse(String errorCode, String... args){
        if(!condition){
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    public EnsureParamBooleanExtension isTrue(String errorCode, String... args){
        if(condition){
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }
}
