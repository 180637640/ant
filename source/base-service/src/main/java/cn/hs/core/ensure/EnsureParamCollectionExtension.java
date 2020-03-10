package cn.hs.core.ensure;


import java.util.Collection;

/**
 * 断言判断
 * @author swt
 */
public class EnsureParamCollectionExtension extends EnsureParam<Collection> {

    private Collection collection;

    public EnsureParamCollectionExtension(Collection collection) {
        super(collection);
        this.collection =collection;
    }

    public EnsureParamCollectionExtension isEmpty(String errorCode, String... args){
        if(collection != null && collection.isEmpty()){
            return this;
        } else {
            throw XExceptionFactory.create(errorCode, args);
        }
    }

    public EnsureParamCollectionExtension isNull(String errorCode, String... args) {
        if(collection == null){
            throw XExceptionFactory.create(errorCode, args);
        }
        return this;
    }
}
