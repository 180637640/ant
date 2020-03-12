package cn.hs.config;


import com.zaxxer.hikari.HikariConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * 过滤器
 * @author swt
 */
public class HikariConfigurationUtil {

   public static HikariConfig loadConfiguration(Properties properties, String prefix) {
      Properties hikariProps = printProperties(properties, prefix);
      return new HikariConfig(hikariProps);
   }

   private static Properties printProperties(Properties properties, String prefix) {
      Properties prop = new Properties();
      for (Object key : properties.keySet()) {
         String k = key.toString();
         if(!k.startsWith(prefix)) {
            continue;
         }
         String newKey = k.substring(prefix.length());
         prop.setProperty(getKey(newKey), properties.getProperty(k));
      }
      return prop;
   }

   /**
    * 改成驼峰法命名
    * @param key	原Key
    * @return		新Key
    */
   private static String getKey(String key) {
      String[] ks = StringUtils.split(key, '-');
      if(null == ks || ks.length <= 1) {
         return key;
      }
      for(int i = 1; i < ks.length; i++) {
         if(null == ks[i] || ks[i].length() <= 1) {
            continue;
         }
         ks[i] = ks[i].substring(0,1).toUpperCase() + ks[i].substring(1);
      }
      return StringUtils.join(ks, "");
   }

}
