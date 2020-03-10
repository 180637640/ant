package cn.hs.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限验证
 * @author swt
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissions {

    /**
     * 权限值集合
     */
    String[] value();
    
    /**
     * 关系判断
     */
    Logical logical() default Logical.AND; 

}

