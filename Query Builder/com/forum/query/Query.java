package com.forum.query;

public interface Query {

	public void setTable(String table);

	public void addColumn(String column);

	public void removeColumn(String column);

	public void addValue(String value);

	public void removeValue(String value);

}
