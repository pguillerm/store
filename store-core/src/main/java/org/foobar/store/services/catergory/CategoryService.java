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
package org.foobar.store.services.catergory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.foobar.store.commons.dao.Identifiable;
import org.foobar.store.commons.exceptions.DaoException;
import org.foobar.store.dao.DaoSecurity;

// TODO: Auto-generated Javadoc
/**
 * CategoryService.
 *
 * @author pguillerm
 * @since 13 févr. 2015
 */
@Named
public class CategoryService implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -9155013488861111632L;

    /** The dao. */
    @Inject
    private DaoSecurity       daoSecurity;

    // =========================================================================
    // METHODS
    // =========================================================================

    // =========================================================================
    // DAO
    // =========================================================================
    /**
     * Gets the by uid.
     *
     * @param <E> the element type
     * @param <PK> the generic type
     * @param type the type
     * @param uid the uid
     * @return the by uid
     * @throws DaoException the dao exception
     */
    public <E extends Identifiable<PK>, PK extends Serializable> E getByUid(Class<E> type, PK uid) throws DaoException {
        return daoSecurity.getByUid(type, uid);
    }

    /**
     * Gets the.
     *
     * @param <E> the element type
     * @param entity the entity
     * @return the e
     * @throws DaoException the dao exception
     */
    public <E extends Identifiable<?>> E get(E entity) throws DaoException {
        return daoSecurity.get(entity);
    }

    /**
     * Refresh.
     *
     * @param <E> the element type
     * @param entity the entity
     * @throws DaoException the dao exception
     */
    public <E extends Identifiable<?>> void refresh(E entity) throws DaoException {
        daoSecurity.refresh(entity);
    }

    /**
     * Save.
     *
     * @param <E> the element type
     * @param entity the entity
     * @throws DaoException the dao exception
     */
    public <E extends Identifiable<?>> void save(E entity) throws DaoException {
        daoSecurity.save(entity);
    }

    /**
     * Save.
     *
     * @param <E> the element type
     * @param listEntity the list entity
     * @throws DaoException the dao exception
     */
    public <E extends Identifiable<?>> void save(List<E> listEntity) throws DaoException {
        daoSecurity.save(listEntity);
    }

    /**
     * Merge.
     *
     * @param <E> the element type
     * @param entity the entity
     * @return the e
     * @throws DaoException the dao exception
     */
    public <E extends Identifiable<?>> E merge(E entity) throws DaoException {
        return daoSecurity.merge(entity);
    }

    /**
     * Merge.
     *
     * @param <E> the element type
     * @param listEntity the list entity
     * @throws DaoException the dao exception
     */
    public <E extends Identifiable<?>> void merge(List<E> listEntity) throws DaoException {
        daoSecurity.merge(listEntity);
    }

    /**
     * Find all.
     *
     * @param <E> the element type
     * @param type the type
     * @return the list
     * @throws DaoException the dao exception
     */
    public <E extends Identifiable<?>> List<E> findAll(Class<E> type) throws DaoException {
        return daoSecurity.findAll(type);
    }

    /**
     * Count.
     *
     * @param type the type
     * @return the int
     * @throws DaoException the dao exception
     */
    public int count(Class<? extends Identifiable<? extends Serializable>> type) throws DaoException {
        return daoSecurity.count(type);
    }

    /**
     * Count.
     *
     * @param filters the filters
     * @param type the type
     * @return the int
     * @throws DaoException the dao exception
     */
    public int count(Map<String, String> filters, Class<? extends Identifiable<? extends Serializable>> type)
            throws DaoException {
        return daoSecurity.count(filters, type);
    }

    /**
     * Delete.
     *
     * @param <E> the element type
     * @param <PK> the generic type
     * @param entity the entity
     * @throws DaoException the dao exception
     */
    public <E extends Identifiable<PK>, PK extends Serializable> void delete(E entity) throws DaoException {
        daoSecurity.delete(entity);
    }

    /**
     * Delete.
     *
     * @param <E> the element type
     * @param <PK> the generic type
     * @param uid the uid
     * @param type the type
     * @throws DaoException the dao exception
     */
    public <E extends Identifiable<PK>, PK extends Serializable> void delete(PK uid, Class<E> type) throws DaoException {
        daoSecurity.delete(uid, type);
    }

    /**
     * Find.
     *
     * @param <E> the element type
     * @param type the type
     * @param first the first
     * @param pageSize the page size
     * @param field the field
     * @param sortOrder the sort order
     * @param filters the filters
     * @return the list
     * @throws DaoException the dao exception
     */
    public <E extends Identifiable<?>> List<E> find(Class<E> type, int first, int pageSize, String field,
                                                    String sortOrder, Map<String, String> filters) throws DaoException {
        return daoSecurity.find(type, first, pageSize, field, sortOrder, filters);
    }

}
