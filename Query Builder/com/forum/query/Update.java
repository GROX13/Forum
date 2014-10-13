package com.forum.query;

import java.util.ArrayList;

public class Update implements Query {
	private String table;
	private ArrayList<String> values;
	private ArrayList<String> columns;

	public Update() {
		this.table = "";
		this.values = new ArrayList<String>();
		this.columns = new ArrayList<String>();
	}

	@Override
	public void setTable(String table) {
		this.table = table;
	}

	@Override
	public void addColumn(String column) {
		this.columns.add(column);
	}

	@Override
	public void addValue(String value) {
		this.values.add(value);
	}

	@Override
	public String toString() {
		String query = "UPDATE " + table + " VALUES ";
		if (this.columns.size() == this.values.size()) {
			for (int i = 0; i < this.columns.size(); i++) {
				query = query + this.columns.get(i) + " = "
						+ this.values.get(i) + ", ";
			}
		}
		return query;
	}

	@Override
	public void removeColumn(String column) {
		this.columns.remove(column);
	}

	@Override
	public void removeValue(String value) {
		this.values.remove(value);
	}

}
