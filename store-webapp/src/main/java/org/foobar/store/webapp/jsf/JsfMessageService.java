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
package org.foobar.store.webapp.jsf;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Named;

// TODO: Auto-generated Javadoc
/**
 * JsfMessage.
 *
 * @author pguillerm
 * @since 15 févr. 2015
 */
@Named
public class JsfMessageService implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4575089439948700059L;

    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Adds the message error.
     *
     * @param messages the messages
     * @param params the params
     */
    public void addMessageError(final String messages, final Object... params) {
        addMessage(null, FacesMessage.SEVERITY_ERROR, null, messages, params);
    }

    /**
     * Adds the message error.
     *
     * @param clientId the client id
     * @param messages the messages
     * @param params the params
     */
    public void addMessageError(final String clientId, final String messages, final Object... params) {
        addMessage(clientId, FacesMessage.SEVERITY_ERROR, null, messages, params);
    }

    /**
     * Adds the message.
     *
     * @param severity the severity
     * @param messages the messages
     * @param params the params
     */
    public void addMessage(final Severity severity, final String messages, final Object... params) {
        addMessage(null, severity, null, messages, params);
    }

    /**
     * Adds the message.
     *
     * @param severity the severity
     * @param shortMessage the short message
     * @param messages the messages
     * @param params the params
     */
    public void addMessage(final Severity severity, final String shortMessage, final String messages,
                           final Object... params) {
        addMessage(null, severity, shortMessage, messages, params);
    }

    /**
     * Adds the message.
     *
     * @param clientId the client id
     * @param severity the severity
     * @param shortMessage the short message
     * @param message the messages
     * @param params the params
     */
    public void addMessage(final String clientId, final Severity severity, final String shortMessage,
                           final String message, final Object... params) {

        final String msg = params == null || params.length == 0 ? message : MessageFormat.format(message, params);
        final String shortMsg = shortMessage == null ? msg : shortMessage;

        final FacesMessage faceMsg = new FacesMessage(severity, shortMsg, msg);
        FacesContext.getCurrentInstance().addMessage(clientId, faceMsg);
    }
}
