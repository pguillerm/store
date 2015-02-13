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
package org.foobar.store.webapp.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.foobar.store.commons.exceptions.DaoException;
import org.foobar.store.model.entities.Category;
import org.foobar.store.model.entities.Product;
import org.foobar.store.services.catergory.CategoryService;

/**
 * CategoryAction
 * 
 * @author pguillerm
 * @since 13 févr. 2015
 */
@RequestScoped
@Named
public class CategoryAction implements Serializable {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1711463293584136564L;

    @Inject
    private CategoryService   categoryService;

    private String            category;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    public void init() throws DaoException {
        if (category != null) {

        }
    }

    // =========================================================================
    // METHODS
    // =========================================================================

    // =========================================================================
    // OVERRIDES
    // =========================================================================

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

    public List<Product> getProducts() throws DaoException {

        Category currentCategory = categoryService.getByUid(Category.class, category);

        if (currentCategory == null) {
            return new ArrayList<>();
        } else {
            return currentCategory.getProducts();
        }

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) throws DaoException {
        this.category = category;
        init();
    }

}
