package cn.hs;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Tests {

	public static void main(String[] args) throws IOException {
		test();
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
