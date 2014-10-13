package com.forum.query;

public class Delete implements Query {
	private String table;

	public Delete() {
		this.table = "";
	}

	@Override
	public void setTable(String table) {
		this.table = table;
	}

	@Override
	public void addColumn(String column) {
		System.err.println("Can't add column in delete statement!");
	}

	@Override
	public void removeColumn(String column) {
		System.err.println("Can't remove column in delete statement!");
	}

	@Override
	public void addValue(String value) {
		System.err.println("Can't add value in delete statement!");
	}

	@Override
	public void removeValue(String value) {
		System.err.println("Can't remove value in delete statement!");
	}

	@Override
	public String toString() {
		return "DELETE FROM " + table;
	}

}
