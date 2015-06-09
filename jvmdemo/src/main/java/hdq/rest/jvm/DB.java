package hdq.rest.jvm;

import java.util.HashMap;

/*
模拟了 数据库,表,条目
*/
// 需要个单例 方所有的table elements
// DB不销毁，table就不会被回收  
public class DB {
	private static DB db;
	private HashMap<String, Table> tableCache = new HashMap<String, Table>();

	private DB() {
	}

	public static DB getInstance() {
		if (db == null) {
			db = new DB();
		}
		return db;
	}

	public Table createTable(String tname) {
		if (tableCache.get(tname) != null) {
			return tableCache.get(tname);
		} else {
			Table table = new Table(tname);
			tableCache.put(tname, table);
			return table;
		}
	}

	public Table getTable(String tname) throws Exception {
		if (tableCache.get(tname) != null) {
			return tableCache.get(tname);
		} else {
			throw new Exception(tname + ": 不存在,请先创建");
		}
	}

	public QueryManager createQueryManager() {
		return null;
	}
}
