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
package org.foobar.store.exceptions;

import org.foobar.store.commons.exceptions.TechnicalException;

/**
 * The Class StoreTechnicalException.
 * @author pguillerm
 * @since 1 févr. 2015
 */
public class StoreTechnicalException extends TechnicalException {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6329682817158416402L;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new store technical exception.
     */
    public StoreTechnicalException() {
        super();
    }

    /**
     * Instantiates a new store technical exception.
     *
     * @param message the message
     * @param values the values
     */
    public StoreTechnicalException(String message, Object... values) {
        super(message, values);
    }

    /**
     * Instantiates a new store technical exception.
     *
     * @param message the message
     * @param cause the cause
     * @param values the values
     */
    public StoreTechnicalException(String message, Throwable cause, Object... values) {
        super(message, cause, values);
    }

    /**
     * Instantiates a new store technical exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public StoreTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new store technical exception.
     *
     * @param message the message
     */
    public StoreTechnicalException(String message) {
        super(message);
    }

    /**
     * Instantiates a new store technical exception.
     *
     * @param cause the cause
     */
    public StoreTechnicalException(Throwable cause) {
        super(cause);
    }

}
