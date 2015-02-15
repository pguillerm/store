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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.foobar.store.commons.dao.Identifiable;

/**
 * Picture
 * 
 * @author pguillerm
 * @since 15 févr. 2015
 */
@Entity
public class Picture implements Identifiable<Long> {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1527056324232626967L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long              uid;

    private String            type;

    private String            name;

    @Lob
    private String            content;

    // =========================================================================
    // OVERRIDES
    // =========================================================================
    public Picture() {
        super();
    }

    public Picture(String hexa, String contentType, String name) {
        super();
        this.content = hexa;
        this.type = contentType;
        this.name = name;
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
        boolean result = this == obj;
        Product other = null;

        if (!result && obj != null && obj instanceof Product) {
            other = (Product) obj;
        }

        if (other != null) {
            result = uid.equals(other.getUid());
        }

        return result;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
