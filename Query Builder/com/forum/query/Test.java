package com.forum.query;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractFactory a = FactoryProducer.getFactory("ACCOUNT");
		AbstractFactory b = FactoryProducer.getFactory("QUERY");
		Account acc = a.getAccount("USER", "GR", "Giorgi", "Rokhadze");
		Query qry = b.getQuery("DELETE");

		System.out.println(acc);
		System.out.println(qry);
		
		WhereDecorator wd = new WhereDecorator(qry);
		
		System.out.println(wd);
	}

}
