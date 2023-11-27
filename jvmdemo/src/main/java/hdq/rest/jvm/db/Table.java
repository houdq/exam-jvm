package hdq.rest.jvm.db;

import java.util.ArrayList;
import java.util.HashMap;

public class Table {
	private String tableName;
	private ArrayList<Element> elements;

	public Table(String tname) {
		elements = new ArrayList<Element>();
		this.tableName = tname;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
		// TODO 这里如果要修改了表名,需要通知一下DB中得缓存,跟着更新一下 可以使用一个Observe
	}

	public ArrayList<Element> query(HashMap<String, String> query) {
		// TODO 这个接口可以作为一个通用查询接口,可以写的复杂一点,
		ArrayList<Element> result = new ArrayList<Element>();
		return result;
	}

	public ArrayList<Element> queryByState(String state) {
		System.out.println(state);
		ArrayList<Element> result = new ArrayList<Element>();
		for (Element e : this.elements) {
			if (e.getState().equals(state)) {
				result.add(e);
			}
		}
		return result;
	}

	public ArrayList<Element> getAll() {
		return elements;
	}

	public void add(Element ele) {
		this.elements.add(ele);
	}
}
