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
package org.foobar.store.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.foobar.store.commons.dao.Identifiable;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Category
 * 
 * @author pguillerm
 * @since 1 févr. 2015
 */
@Entity
public class Category implements Identifiable<String> {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1276246335753875817L;

    @Id
    @NotEmpty
    private String            uid;

    @NotEmpty
    private String            label;

    @OneToMany(targetEntity = Product.class, mappedBy = "category")
    private List<Product>     products;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    public Category() {
        super();
    }

    public Category(String uid) {
        super();
        this.uid = uid;
    }

    public Category(String uid, String label) {
        super();
        this.uid = uid;
        this.label = label;
    }

    public Category(String uid, String label, List<Product> products) {
        super();
        this.uid = uid;
        this.label = label;
        this.products = products;
    }

    // =========================================================================
    // OVERRIDES
    // =========================================================================
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if (uid == null) {
            if (other.uid != null)
                return false;
        } else if (!uid.equals(other.uid))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Category [uid=" + uid + ", label=" + label + "]";
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

    @Override
    public boolean isUidSet() {
        return uid != null && !uid.isEmpty();
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(final String uid) {
        this.uid = uid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
