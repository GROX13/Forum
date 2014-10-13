package com.forum.query;

import java.util.HashMap;

public class QueryFactory extends AbstractFactory {
	private HashMap<String, Query> qry;

	public QueryFactory() {
		qry = new HashMap<String, Query>();
		qry.put("INSERT", new Insert());
		qry.put("DELETE", new Delete());
		qry.put("SELECT", new Select());
		qry.put("UPDATE", new Update());
	}

	@Override
	Query getQuery(String queryType) {
		return qry.get(queryType);
	}

	@Override
	Account getAccount(String accountType, String usrnm, String nm, String srnm) {
		return null;
	}

}
