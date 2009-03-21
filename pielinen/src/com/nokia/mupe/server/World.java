/* 
 * The contents of this file are subject to the NOKOS License Version 1.0 
 * (the "License"); 
 * you may not use this file except in compliance with the License. 
 * Software distributed under the License is distributed on an "AS IS" basis, 
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. 
 * See the License for the specific language governing rights and 
 * limitations under the License. 
 * The Original Software is MUPE - Multi User Publishing Environment. 
 * Copyright (c) 2003 Nokia and others. 
 * All Rights Reserved. 
 * Author: Ari Koivisto 
 * Contributor(s): Riku Suomela
 */

package com.nokia.mupe.server;

/**
 * World is the world used in each application.
 * 
 * Change the virtual world initialisation in this class. The world is created
 * as follows:
 * 
 * ObjectID: 0 - this world object. Customize this file.
 * 
 * ObjectID 1: defaultRoom: created in createDefaultRoom. Define the Room object
 * that you are using in this method, but do not create objects into the room!
 * 
 * ObjectID: 2 - ContextManager. Created in createContextManager, cutomize if
 * needed.
 * 
 * Object ID: rest - create any other world content in createWorldContent().
 * 
 * @version 1.20
 * @since 1.13
 */
public class World extends AbstractWorld {

    /**
     * Constructor for the World instance used in this application. The actual
     * content is created in the create...() methods.
     * 
     * @param serviceName Name of this service.
     * @param parser Reference to parser.
     * @throws MupeException
     */
    public World(String serviceName, Parser parser) throws MupeException {
        super(serviceName, parser);
        createWorldContent();
    }
    
    /**
     * @see com.nokia.mupe.server.MupeWorldContentBuilderInterface#createDefaultRoom()
     */
    public Room createDefaultRoom() throws MupeException {
    	return new ItemsList(null, null, "DefaultRoom"); 
    }

    /**
     * @see com.nokia.mupe.server.MupeWorldContentBuilderInterface#createObserverRoom()
     */
    public Room createObserverRoom() throws MupeException {
        return new ObserverRoom(null, null);
    }

    /**
     * @see com.nokia.mupe.server.MupeWorldContentBuilderInterface#createContextManager()
     */
    public ContextManager createContextManager() throws MupeException {
        return new ContextManager(null, null);
    }

    /**
     * Called by the client when creating a new user account. Extra information
     * about the client may be requested by altering the subscribe.xml, here a
     * type is returned, telling whether to create an observer or a normal user.
     * 
     * @param caller
     * @param type type of user to create
     * @return creator form
     * @throws MupeException
     */
    public String clientCreateUser(User caller, String type)
            throws MupeException {
        if (type.equals("observer")) {
            return ObserverUser.clientGetCreator(caller, "ObserverUser", observerRoom().idString());
        } else {
            return Person.clientGetCreator(caller, "Person");
        }
    }

    /**
     * @see com.nokia.mupe.server.MupeWorldContentBuilderInterface#createWorldContent()
     */
    public void createWorldContent() throws MupeException {
        // TODO add your own content creation here
    }    
    
    // ***** PRIVATE *****

    private static final long serialVersionUID = 1010;
}

