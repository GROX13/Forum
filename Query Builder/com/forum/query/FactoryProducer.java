package com.forum.query;

public class FactoryProducer {
	 public static AbstractFactory getFactory(String choice){
		 if(choice.equalsIgnoreCase("ACCOUNT")){
	         return new AccountFactory();
	      } else if(choice.equalsIgnoreCase("QUERY")){
	         return new QueryFactory();
	      }
	      return null;
	   }
}
