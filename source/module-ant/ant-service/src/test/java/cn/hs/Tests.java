package cn.hs;

import com.zaxxer.hikari.HikariConfig;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Tests {

	public static void main(String[] args) throws IOException {
		printProperties();
	}

	public static void printProperties() throws IOException {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(Paths.get("/Users/songwentao/git/hs/etl/doc/ant/task_p2m.properties").toFile());
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String CONFIG_PREFIX = "source.endpoint.datasource.";
		Properties p = printProperties(properties, CONFIG_PREFIX);

		for (Object key : p.keySet()) {
			System.out.println(String.format("%s \t\t %s", key, p.get(key)));
		}

		HikariConfig config = new HikariConfig(p);
		System.out.println(config.getDriverClassName());
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


	public static void printColumn() throws IOException {
//		List<String> list = Files.readAllLines(Paths.get("/Users/songwentao/git/2019/table.data_center.txt"));
		List<String> list = Files.readAllLines(Paths.get("/Users/songwentao/git/2019/table.xt.txt"));

		// 检查
		for (String val : list) {
			String[] as = StringUtils.split(val, '\t');
			if(null == as || as.length != 3) {
				System.out.println("格式不正确：" + val);
				return;
			}
		}

		System.out.println("格式检查通过");

		List<String> columnList = new ArrayList<>();
		String tableName = "";
		for (String val : list) {
			String[] as = StringUtils.split(val, '\t');
			if(!StringUtils.equalsAnyIgnoreCase(as[0], tableName)) {
				System.out.println(tableName);
				System.out.println(StringUtils.join(columnList, ","));
				System.out.println("============================================================================== ");
				columnList.clear();
				tableName = as[0];
			}
			columnList.add(as[1] + "|" + as[2]);
		}
	}

	public static void test() throws IOException {
		List<String> list = Files.readAllLines(Paths.get("/Users/songwentao/git/2019/table.txt"));

		String tableName = "";
		List<String> columnList = new ArrayList<>();
		for (String val : list) {
			String[] as = StringUtils.split(val, '\t');
			if(!StringUtils.equalsAnyIgnoreCase(as[0], tableName)) {
				System.out.println(tableName);
				System.out.println(StringUtils.join(columnList, ","));
				System.out.println(" ====== ");
				columnList.clear();
				tableName = as[0];
			}
			columnList.add(as[1]);
		}
	}

}
