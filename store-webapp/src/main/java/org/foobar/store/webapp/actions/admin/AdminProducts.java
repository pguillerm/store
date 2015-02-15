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
package org.foobar.store.webapp.actions.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.foobar.store.commons.exceptions.DaoException;
import org.foobar.store.commons.utils.JsfUtils;
import org.foobar.store.model.entities.Category;
import org.foobar.store.model.entities.Picture;
import org.foobar.store.model.entities.Product;
import org.foobar.store.services.catergory.CategoryService;
import org.foobar.store.services.pictures.PicturesService;
import org.foobar.store.services.product.ProductService;
import org.foobar.store.webapp.jsf.JsfMessageService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.webapp.MultipartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AdminCategor
 * 
 * @author pguillerm
 * @since 1 févr. 2015
 */
@SessionScoped
@Named
public class AdminProducts implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long   serialVersionUID = 2181039578424633463L;

    private static final Logger LOGGER           = LoggerFactory.getLogger(AdminProducts.class);

    @Inject
    private ProductService      productService;

    @Inject
    private CategoryService     categoryService;

    @Inject
    private PicturesService     picturesService;

    @Inject
    private JsfMessageService   jsfMessage;

    private Product             product;

    private List<Picture>       pictures         = new ArrayList<>();

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    @PostConstruct
    public void init() throws DaoException {
        product = new Product();
    }

    // =========================================================================
    // METHODS
    // =========================================================================
    public String add() throws DaoException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("save product : {}", product);
        }
        productService.save(product);
        init();
        return JsfUtils.getInstance().sendPostRedirectGet();
    }

    public String delete(final Product product) throws DaoException {
        productService.delete(product);
        return JsfUtils.getInstance().sendPostRedirectGet();
    }

    public void handleFileUpload(FileUploadEvent event) {
        //@formatter:off
      Picture pic = null;
      try {
          pic = picturesService.saveFromFile(event.getFile().getContents(),
                                                           event.getFile().getContentType(),
                                                           event.getFile().getFileName());
      } catch (DaoException e) {
          jsfMessage.addMessageError("error in uploading file");
      }
      //@formatter:on

        if (pic != null) {
            pictures.add(pic);
        }
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

    public List<Category> getCategories() throws DaoException {
        return categoryService.findAll(Category.class);
    }

    public List<Product> getProducts() throws DaoException {
        return productService.findAll(Product.class);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
