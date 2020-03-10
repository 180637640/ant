package cn.hs.core.ensure;


import org.apache.commons.lang3.StringUtils;

/**
 * 断言判断
 * @author swt
 */
public class EnsureParamStringExtension extends EnsureParam<String> {
    private String string;

    public EnsureParamStringExtension(String string) {
        super(string);
        this.string = string;
    }

    public EnsureParamStringExtension isBlank(String errorCode, String... args) {
        if (StringUtils.isBlank(string)) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    public EnsureParamStringExtension isNotBlank(String errorCode, String... args) {
        if (StringUtils.isNotBlank(string)) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    public EnsureParamStringExtension equals(String comparedString, String errorCode, String... args) {
        if (string.equals(comparedString)) {
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

    public EnsureParamStringExtension isNull(String errorCode, String... args){
        if(null == string){
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }

}
