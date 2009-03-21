/* 
 * The contents of this file are subject to the NOKOS License Version 1.0 
 * (the "License"); 
 * you may not use this file except in compliance with the License. 
 * Software distributed under the License is distributed on an "AS IS" basis, 
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. 
 * See the License for the specific language governing rights and 
 * limitations under the License. 
 * The Original Software is MUPE - Multi User Publishing Environment. 
 * Copyright (c) 2003-2006 Nokia and others. 
 * All Rights Reserved. 
 * Author: <add_your_name>
 * Contributor(s): <add_list> 
 */

package com.nokia.mupe.server;

public class Person extends User {
	private String btid = "";
	/**
	 * Constructor for User objects.
	 * 
	 * @param caller
	 * @param location
	 *            initial location of this user
	 * @param name
	 *            name for this object
	 * @throws MupeException
	 */
	public Person(User caller, Base location, String name, String btid)
			throws MupeException {
		super(caller, location, name);
		setBtid(btid);
	}

	private static final long serialVersionUID = 1010;

	public String getBtid() {
		return btid;
	}

	public void setBtid(String btid) {
		this.btid = btid;
	}
}
