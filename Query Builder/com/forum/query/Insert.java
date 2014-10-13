package com.forum.query;

import java.util.ArrayList;

public class Insert implements Query {
	private String table;
	private ArrayList<String> values;

	public Insert() {
		this.table = "";
		this.values = new ArrayList<String>();
	}

	@Override
	public void setTable(String table) {
		this.table = table;
	}

	@Override
	public void addColumn(String column) {
		System.err.println("Can't add column in insert statement!");
	}

	@Override
	public void addValue(String value) {
		this.values.add(value);
	}

	@Override
	public String toString() {
		String query = "INSER INTO " + table + " VALUES ";
		if (this.values.size() > 0) {
			int sz = this.values.size() - 1;
			for (int i = 0; i < sz; i++) {
				query = query + this.values.get(i) + ", ";
			}
			query = query + this.values.get(sz);
		}
		return query;
	}

	@Override
	public void removeColumn(String column) {
		System.err.println("Can't add column in insert statement!");
	}

	@Override
	public void removeValue(String value) {
		this.values.remove(value);
	}

}
