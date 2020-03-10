package cn.hs.core.ensure;


import java.util.Collection;

/**
 * 断言
 * @author swt
 */
public class Ensure {

    public static EnsureParamBooleanExtension that(boolean obj){
        return new EnsureParamBooleanExtension(obj);
    }

    public static <T extends Collection> EnsureParamCollectionExtension that(T obj){
        return new EnsureParamCollectionExtension(obj);
    }

    public static <T extends Boolean> EnsureParamBooleanExtension that(T obj){
        return new EnsureParamBooleanExtension(obj);
    }

    public static <T extends Number> EnsureParamNumberExtension that (T obj){
        return new EnsureParamNumberExtension(obj);
    }

    public static EnsureParamIntegerExtension that (Integer obj){
        return new EnsureParamIntegerExtension(obj);
    }

    public static <T extends Enum> EnsureParamEnumExtension that (T obj){
        return new EnsureParamEnumExtension(obj);
    }

    public static EnsureParamStringExtension that (String obj){
        return new EnsureParamStringExtension(obj);
    }

}
