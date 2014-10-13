package com.forum.query;

import java.util.ArrayList;

public class WhereDecorator extends QueryDecorator {
	private ArrayList<String> whereColumn;
	private ArrayList<String> operator;
	private ArrayList<String> whereValue;

	public WhereDecorator(Query query) {
		super(query);
		this.whereColumn = new ArrayList<String>();
		this.operator = new ArrayList<String>();
		this.whereValue = new ArrayList<String>();
	}

	public void addOperator(String operator) {
		this.operator.add(operator);
	}

	public void removeOperator(String operator) {
		this.operator.remove(operator);
	}

	public void addWhereColumn(String column) {
		this.whereColumn.add(column);
	}

	public void removeWhereColumn(String column) {
		this.whereColumn.remove(column);
	}

	public void addWhereValue(String value) {
		this.whereValue.add(value);
	}

	public void removeWhereValue(String value) {
		this.whereValue.remove(value);
	}

	@Override
	public String toString() {
		String decoration = " WHERE ";
		if (this.whereColumn.size() == this.whereValue.size()
				&& this.whereColumn.size() == this.operator.size())
			for (int i = 0; i < this.whereColumn.size(); i++) {
				decoration = decoration + whereColumn.get(i) + operator.get(i)
						+ whereValue.get(i);
			}
		return super.toString() + decoration;
	}
}
