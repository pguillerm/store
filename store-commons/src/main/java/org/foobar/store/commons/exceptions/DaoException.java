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
package org.foobar.store.commons.exceptions;

/**
 * The Class DaoException.
 * @author pguillerm
 * @since 13 févr. 2015
 */
public class DaoException extends TechnicalException {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8736427456332806808L;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new dao exception.
     */
    public DaoException() {
        super();
    }

    /**
     * Instantiates a new dao exception.
     *
     * @param message the message
     * @param values the values
     */
    public DaoException(final String message, final Object... values) {
        super(message, values);
    }

    /**
     * Instantiates a new dao exception.
     *
     * @param message the message
     * @param cause the cause
     * @param values the values
     */
    public DaoException(final String message, final Throwable cause, final Object... values) {
        super(message, cause, values);
    }

    /**
     * Instantiates a new dao exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public DaoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new dao exception.
     *
     * @param message the message
     */
    public DaoException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new dao exception.
     *
     * @param cause the cause
     */
    public DaoException(final Throwable cause) {
        super(cause);
    }

}
