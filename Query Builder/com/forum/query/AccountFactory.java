package com.forum.query;

import java.util.HashMap;

public class AccountFactory extends AbstractFactory {
	private HashMap<String, Account> acc;

	public AccountFactory() {
		acc = new HashMap<String, Account>();
		acc.put("USER", new User());
		acc.put("GUEST", new Guest());
	}

	@Override
	Query getQuery(String queryType) {
		return null;
	}

	@Override
	Account getAccount(String accountType, String usrnm, String nm,
			String srnm) {
		Account a = acc.get(accountType);
		a.setName(nm);
		a.setSurname(srnm);
		a.setUsername(usrnm);
		return a;
	}

}
