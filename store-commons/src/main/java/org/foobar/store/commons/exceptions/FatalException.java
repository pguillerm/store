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

import java.text.MessageFormat;

/**
 * FatalException.
 *
 * @author pguillerm
 * @since 13 févr. 2015
 */
public class FatalException extends RuntimeException {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7826304416505584698L;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new fatal exception.
     */
    public FatalException() {
        super();
    }

    /**
     * Instantiates a new fatal exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public FatalException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new fatal exception.
     *
     * @param message the message
     */
    public FatalException(String message) {
        super(message);
    }

    /**
     * Instantiates a new fatal exception.
     *
     * @param cause the cause
     */
    public FatalException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new fatal exception.
     *
     * @param message the message
     * @param values the values
     */
    public FatalException(String message, Object... values) {
        super(MessageFormat.format(message, values));
    }

    /**
     * Instantiates a new fatal exception.
     *
     * @param message the message
     * @param cause the cause
     * @param values the values
     */
    public FatalException(String message, Throwable cause, Object... values) {
        super(MessageFormat.format(message, values), cause);
    }

}
