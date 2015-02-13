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
 * TechnicalException
 * 
 * @author pguillerm
 * @since 13 févr. 2015
 */
public class TechnicalException extends Exception {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1435550907961500852L;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    public TechnicalException() {
        super();
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }

    public TechnicalException(String message, Object... values) {
        super(MessageFormat.format(message, values));
    }

    public TechnicalException(String message, Throwable cause, Object... values) {
        super(MessageFormat.format(message, values), cause);
    }
}
