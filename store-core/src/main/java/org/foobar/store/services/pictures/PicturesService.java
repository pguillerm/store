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
package org.foobar.store.services.pictures;

import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;
import org.foobar.store.commons.exceptions.DaoException;
import org.foobar.store.dao.DaoSecurity;
import org.foobar.store.model.entities.Picture;

/**
 * PicturesService
 * 
 * @author pguillerm
 * @since 15 févr. 2015
 */
@Named
public class PicturesService extends DaoSecurity {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7374139424562837631L;

    // =========================================================================
    // METHODS
    // =========================================================================

    public Picture saveFromFile(byte[] content, String contentType, String name) throws DaoException {

        if (content == null || contentType == null) {
            throw new DaoException("content or content type mustn't be null !");
        }

        
        final String hexa = new String(Base64.encodeBase64(content));

        final Picture picture = new Picture(hexa, contentType, name);

        save(picture);

        return picture;
    }
}
