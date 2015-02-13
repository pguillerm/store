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
package org.foobar.store.webapp.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import org.foobar.store.commons.exceptions.DaoException;
import org.foobar.store.model.entities.Category;
import org.foobar.store.services.catergory.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CategoryConvertor
 * 
 * @author pguillerm
 * @since 13 févr. 2015
 */
@FacesConverter("CategoryConverter")
@Named
public class CategoryConverter implements Converter {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    @Inject
    private CategoryService     categoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryConverter.class);

    // =========================================================================
    // METHODS
    // =========================================================================
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Category result = null;
        try {
            result = categoryService.getByUid(Category.class, value);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = null;

        if (value instanceof Category) {
            result = ((Category) value).getUid();
        }
        return result;
    }
    // =========================================================================
    // OVERRIDES
    // =========================================================================

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
}
