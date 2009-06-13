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

import com.nokia.mupe.server.data.*;

/**
 * Base class extensions by users should all be put here. 
 * 
 * @version 1.13
 * @since 1.13
 */
public abstract class BaseExtensions extends Base {

    /**
     * Superclass constructor for all content classes. Sets default xml
     * description file names.
     * 
     * @param caller
     * @param location initial location of this object
     * @param name name for the object
     * @throws MupeException
     */
    public BaseExtensions(User caller, Base location, String name) throws MupeException {
        super(caller, location, name);
    }
    
    private static final long serialVersionUID = 1200;    
}

