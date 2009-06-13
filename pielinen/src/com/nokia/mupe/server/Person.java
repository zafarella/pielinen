package com.nokia.mupe.server;

public class Person extends User {

	private static final long serialVersionUID = 1010;

	public Person(User caller, Base location, String name, String btid) throws MupeException {
		super(caller, location, name);
	}
}
