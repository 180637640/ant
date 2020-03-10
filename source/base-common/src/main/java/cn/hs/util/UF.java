package cn.hs.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 常用函数
 * @author swt
 */
public class UF extends UserFunction{
    public static final String INVITE_CODE_PREFIX = "HS";

    /**
     * 过滤非数字字符串
     * @param value     字符串
     * @return          格式化后的字符串
     */
    public static String toStringWithNum(String value) {
        if(StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        if(value.startsWith("-")) {
            return null;
        }

        return value;
    }

    /**
     * double转成字符串
     * @param value     double
     * @param pattern   格式 #.## （1.234 -> 1.23 | 1.1 -> 1.1） #。00 （1.234 -> 1.234 | 1.1 -> 1.10）
     * @return          格式化后的字符串
     */
    public static String toString(double value, String pattern) {
        if(StringUtils.isBlank(pattern)) {
            pattern = "#.##";
        }
        DecimalFormat df = new DecimalFormat(pattern);

        return df.format(value);
    }

    public static String getInviteCode() {
        String offset = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1000000));
        if (StringUtils.length(offset) < 6) {
            offset = StringUtils.leftPad(offset, 6, '0');
        }
        return INVITE_CODE_PREFIX+offset;
    }

    /**
     * double转成字符串, 保留小数点后两位，不足两位补0
     * @param value     double
     * @return          格式化后的字符串
     */
    public static String toStringWith2P(double value) {
        return toString(value, "#.00");
    }

    /**
     * 通过反射获取Bean中所有属性和属性值
     * @param o Bean
     * @return  属性和属性值
     */
    public static Map<String, String> getFieldMap (Object o) {
        Map<String, String> fieldMap = new HashMap<>();

        try {
            Field[] fields = o.getClass().getDeclaredFields();
            Field.setAccessible(fields, true);
            for (Field field : fields) {
                Object obj = field.get(o);

                if(obj instanceof String || obj instanceof Integer || obj instanceof Double) {
                    fieldMap.put(field.getName(), field.get(o).toString());
                    continue;
                }
            }
            if(null != o.getClass().getGenericSuperclass()) {
                // 如果有父类
                fields = o.getClass().getSuperclass().getDeclaredFields();
                Field.setAccessible(fields, true);
                for (Field field : fields) {
                    Object obj = field.get(o);

                    if(obj instanceof String || obj instanceof Integer || obj instanceof Double) {
                        fieldMap.put(field.getName(), field.get(o).toString());
                        continue;
                    }
                }
            }
        } catch (Exception e) {
        }

        return fieldMap;
    }

    /**
     * 按月份拆分
     * @param minReceiveTime    最小时间
     * @param maxReceiveTime    最大实际
     * @return                  列表（2019-07-17 16:25:01,2019-07-31 23:59:59）
     */
    public static List<String> splitByMonth(String minReceiveTime, String maxReceiveTime) {
        if(StringUtils.isAnyBlank(minReceiveTime, maxReceiveTime)) {
            return null;
        }
        LocalDateTime min = UF.getDateTime(minReceiveTime);
        LocalDateTime max = UF.getDateTime(maxReceiveTime);
        if(null == min || null == max || max.isBefore(min)) {
            return null;
        }
        List<String> list = new ArrayList<>();
        while (true) {
            LocalDateTime index = min;
            if(index.getMonthValue() == max.getMonthValue()) {
                index = max;
            } else {
                // 月底最后一刻
                index = index.plusMonths(1);
                index = LocalDateTime.of(index.getYear(), index.getMonthValue(), 1, 0, 0,0).minusSeconds(1);
            }
            list.add(UF.getFormatDateTime(min) + "," + UF.getFormatDateTime(index));
            if(index.getMonthValue() == max.getMonthValue()) {
                break;
            }
            // 月初
            min = index.plusSeconds(1);
        }
        return list;
    }

    /**
     * 按日份拆分时间
     * @param minReceiveTime    最小时间
     * @param maxReceiveTime    最大实际
     * @return                  列表（2019-07-17 16:25:01,2019-07-17 23:59:59）
     */
    public static List<String> splitByDay(String minReceiveTime, String maxReceiveTime) {
        if(StringUtils.isAnyBlank(minReceiveTime, maxReceiveTime)) {
            return null;
        }
        LocalDateTime min = UF.getDateTime(minReceiveTime);
        LocalDateTime max = UF.getDateTime(maxReceiveTime);
        if(null == min || null == max || max.isBefore(min)) {
            return null;
        }
        List<String> list = new ArrayList<>();
        while (true) {
            LocalDateTime index = min;
            if(index.toLocalDate().isEqual(max.toLocalDate())) {
                index = max;
            } else {
                // 每日最后一刻
                index = index.plusDays(1);
                index = LocalDateTime.of(index.getYear(), index.getMonthValue(), index.getDayOfMonth(), 0, 0,0).minusSeconds(1);
            }
            list.add(UF.getFormatDateTime(min) + "," + UF.getFormatDateTime(index));
            if(index.toLocalDate().isEqual(max.toLocalDate())) {
                break;
            }
            // 月初
            min = index.plusSeconds(1);
        }
        return list;
    }
}
