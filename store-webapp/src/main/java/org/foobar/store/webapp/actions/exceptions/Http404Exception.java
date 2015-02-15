/*
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *  
        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package org.foobar.store.webapp.actions.exceptions;

import org.foobar.store.commons.exceptions.TechnicalException;

/**
 * Http404Exception.
 *
 * @author pguillerm
 * @since 15 févr. 2015
 */
public class Http404Exception extends TechnicalException {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5978146288804229595L;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new http404 exception.
     */
    public Http404Exception() {
        super();
    }

    /**
     * Instantiates a new http404 exception.
     *
     * @param message the message
     * @param values the values
     */
    public Http404Exception(String message, Object... values) {
        super(message, values);
    }

    /**
     * Instantiates a new http404 exception.
     *
     * @param message the message
     * @param cause the cause
     * @param values the values
     */
    public Http404Exception(String message, Throwable cause, Object... values) {
        super(message, cause, values);
    }

    /**
     * Instantiates a new http404 exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public Http404Exception(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new http404 exception.
     *
     * @param message the message
     */
    public Http404Exception(String message) {
        super(message);
    }

    /**
     * Instantiates a new http404 exception.
     *
     * @param cause the cause
     */
    public Http404Exception(Throwable cause) {
        super(cause);
    }
}
