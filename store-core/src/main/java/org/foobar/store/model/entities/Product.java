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

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.foobar.store.commons.dao.Identifiable;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Product
 * 
 * @author pguillerm
 * @since 1 févr. 2015
 */
@Entity
public class Product implements Identifiable<Long> {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7919149490938106258L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long              uid;

    @NotEmpty
    private String            label;

    @Lob
    private String            description;

    private Double            price;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category          category;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    public Product() {
        super();
    }

    public Product(String label, String description, Double price, Category category) {
        super();
        this.label = label;
        this.description = description;
        this.price = price;
        this.category = category;
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
        Product other = (Product) obj;
        if (uid == null) {
            if (other.uid != null)
                return false;
        } else if (!uid.equals(other.uid))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [uid=" + uid + ", label=" + label + ", description=" + description + ", price=" + price
                + ", category=" + category + "]";
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

    @Override
    public Long getUid() {
        return uid;
    }

    @Override
    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Override
    public boolean isUidSet() {
        return uid != null;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
