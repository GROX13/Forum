package com.forum.query;

public abstract class AbstractFactory {

	abstract Query getQuery(String queryType);

	abstract Account getAccount(String accountType, String usrnm, String nm,
			String srnm);

}
