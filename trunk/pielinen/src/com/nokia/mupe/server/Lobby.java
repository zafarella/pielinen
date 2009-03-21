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

public class Lobby extends Room {

	private static final long serialVersionUID = 1L;

	public Lobby(User caller, Base location, String name) throws MupeException {
		super(caller, location, name);
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Returns the names of the users in the room.
     * Modify to suit your needs.
     * 
     * @param caller
     * @return a string with the names.
     * @throws MupeException
     */
    	public String XMLGetContents(User caller) throws MupeException {
    		String retStr = "";
    		for (Base obj : contents()) {
    			if (obj instanceof Person) {
    			    retStr += getDynamicXML("icon.xml", caller, obj.name, "icon_user", obj.id());
    			}
    		}
    		return retStr;
    	}


}
