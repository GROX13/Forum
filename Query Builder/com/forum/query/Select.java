package com.forum.query;

import java.util.ArrayList;

public class Select implements Query {
	private String table;
	private ArrayList<String> columns;

	public Select() {
		this.table = "";
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
	public void addValue(String table) {
		System.err.println("Can't add value in select statement!");
	}

	@Override
	public String toString() {
		String query = "SELECT ";
		if (this.columns.size() > 0) {
			for (int i = 0; i < this.columns.size(); i++) {
				query = query + columns.get(i) +  ", ";
			}
		} else
			query = query + "* FROM " + this.table;
		return query;
	}

	@Override
	public void removeColumn(String column) {
		this.columns.remove(column);
	}

	@Override
	public void removeValue(String value) {
		System.err.println("Can't remove value in select statement!");
	}

}
