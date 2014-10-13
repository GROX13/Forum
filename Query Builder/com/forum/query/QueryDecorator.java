package com.forum.query;

public abstract class QueryDecorator implements Query {
	private Query query;

	public QueryDecorator(Query query) {
		this.query = query;
	}

	@Override
	public void setTable(String table) {
		this.query.setTable(table);
	}

	@Override
	public void addColumn(String column) {
		this.query.addColumn(column);
	}

	@Override
	public void removeColumn(String column) {
		this.query.removeColumn(column);
	}

	@Override
	public void addValue(String value) {
		this.query.addValue(value);
	}

	@Override
	public void removeValue(String value) {
		this.query.removeValue(value);
	}

	@Override
	public String toString() {
		return this.query.toString();
	}

}
