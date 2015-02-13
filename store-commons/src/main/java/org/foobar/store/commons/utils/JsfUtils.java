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
package org.foobar.store.commons.utils;

import javax.faces.context.FacesContext;

/**
 * JsfUtils
 * 
 * @author pguillerm
 * @since 13 févr. 2015
 */
public class JsfUtils {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    public static final String    FACES_EXT                = ".xhtml";

    private static final String   KEY_VIEW_PARAM           = "includeViewParams=true";

    private static final String   KEY_FACES_REDIRECT       = "faces-redirect=true";

    private static final String   KEY_AND                  = "&";

    private static final String   ACTION_POST_REDIRECT     = "%s?" + KEY_VIEW_PARAM;

    private static final String   ACTION_POST_REDIRECT_GET = "%s?" + KEY_FACES_REDIRECT + KEY_AND + KEY_FACES_REDIRECT;


    public static final String    POST_REDIRECT_GET        = "?" + KEY_FACES_REDIRECT + KEY_AND + KEY_VIEW_PARAM;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    private static final JsfUtils INSTANCE                 = new JsfUtils();

    private JsfUtils() {
        super();
    }

    public static JsfUtils getInstance() {
        synchronized (JsfUtils.class) {
            return INSTANCE;
        }
    }

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Allow to send redirect to current page
     * 
     * @return JSF instruction for post redirect
     */
    public String sendRedirect() {
        return sendRedirect(null);
    }

    /**
     * Allow to send redirect to specific page
     * 
     * @param path the path
     * @return the string
     */
    public String sendRedirect(final String path) {
        return postRedirect(path, false);
    }

    /**
     * Allow to send a post redirect get to the current page
     * 
     * @return the string
     */
    public String sendPostRedirectGet() {
        return sendPostRedirectGet(null);
    }

    /**
     * Allow to send a post redirect get to specific page
     * 
     * @param path the path
     * @return the string
     */
    public String sendPostRedirectGet(final String path) {
        return postRedirect(path, true);
    }

    // =========================================================================
    // UTILS
    // =========================================================================
    /**
     * Gets the current page name.
     * 
     * @return the current page name
     */
    protected String getCurrentPageName() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        String pageName = null;
        String path = null;
        if (ctx != null && ctx.getExternalContext() != null) {
            path = ctx.getExternalContext().getRequestServletPath();
        }
        if (path != null && !path.isEmpty() && path.contains(FACES_EXT)) {
            final int lastSubPath = path.lastIndexOf("/") + 1;
            if (path.length() > lastSubPath) {
                pageName = path.substring(lastSubPath);
            }
        }
        return pageName;
    }

    /**
     * Post redirect.
     * 
     * @param path the path
     * @param postRedirectGet the post redirect get
     * @return the string
     */
    private String postRedirect(final String path, boolean postRedirectGet) {
        String result = null;

        final String pageName = getCurrentPageName();
        final String pattern = postRedirectGet ? ACTION_POST_REDIRECT_GET : ACTION_POST_REDIRECT;

        if (path == null || path.isEmpty()) {
            result = String.format(pattern, pageName);
        } else {
            result = String.format(pattern, path);
        }

        return result;
    }
}
