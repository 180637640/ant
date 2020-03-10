package cn.hs.web.cors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

/**
 * 参数过滤，防止XSS攻击
 * @author swt
 */
public class HtmlConverter implements Converter<String, String>{

    @Override
    public String convert(String source) {
        if(StringUtils.isBlank(source)) {
            return source;
        }
        return HtmlUtils.htmlEscape(source);
    }

}
