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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.foobar.store.commons.exceptions.DaoException;
import org.foobar.store.model.entities.Picture;
import org.foobar.store.model.entities.Product;
import org.foobar.store.services.product.ProductService;
import org.foobar.store.webapp.actions.exceptions.Http404Exception;

/**
 * CategoryAction
 * 
 * @author pguillerm
 * @since 13 févr. 2015
 */
@RequestScoped
@Named
public class ProductAction implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2254279521108406661L;

    @Inject
    private ProductService    productService;

    private Long              uid;

    private Product           product;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    private void init() throws DaoException, Http404Exception {
        if (uid != null) {
            product = productService.getByUid(Product.class, uid);

            if (product == null) {
                throw new Http404Exception("Product not found");
            }
        }
    }

    public String getMainImage() {
        StringBuilder result = new StringBuilder();

        if (product.getPictures() != null && !product.getPictures().isEmpty()) {
            final Picture firstPicture = product.getPictures().get(0);
            result.append("data:");
            result.append(firstPicture.getType());
            result.append(";base64,");
            result.append(firstPicture.getContent());
        }
        return result.toString();
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) throws DaoException, Http404Exception {
        this.uid = uid;
        init();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
