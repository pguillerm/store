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
package org.foobar.store.commons.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.foobar.store.commons.exceptions.DaoException;

/**
 * The Interface Dao.
 * 
 * @author pguillerm
 * @since 13 févr. 2015
 */
public interface Dao extends Serializable {

    /**
     * Gets the by id.
     * 
     * @param <E> the element type
     * @param <PK> the generic type
     * @param type the type
     * @param uid the uid
     * @return the by id
     * @throws DaoException the dao exception
     */
    <E extends Identifiable<PK>, PK extends Serializable> E getByUid(final Class<E> type, final PK uid)
            throws DaoException;

    /**
     * Gets the.
     * 
     * @param <E> the element type
     * @param entity the entity
     * @return the e
     * @throws DaoException the dao exception
     */
    <E extends Identifiable<?>> E get(E entity) throws DaoException;

    // =====================================================================
    // REFRESH
    // =====================================================================
    /**
     * Refresh.
     * 
     * @param <E> the element type
     * @param entity the entity
     * @throws DaoException the dao exception
     */
    <E extends Identifiable<?>> void refresh(E entity) throws DaoException;

    // =====================================================================
    // SAVE
    // =====================================================================
    /**
     * Save.
     * 
     * @param <E> the element type
     * @param entity the entity
     * @throws DaoException the dao exception
     */
    <E extends Identifiable<?>> void save(E entity) throws DaoException;

    /**
     * Save.
     * 
     * @param <E> the element type
     * @param listEntity the list entity
     * @throws DaoException the dao exception
     */
    <E extends Identifiable<?>> void save(List<E> listEntity) throws DaoException;

    // =====================================================================
    // MERGE
    // =====================================================================

    /**
     * Merge.
     * 
     * @param <E> the element type
     * @param entity the entity
     * @return the e
     * @throws DaoException the dao exception
     */
    <E extends Identifiable<?>> E merge(E entity) throws DaoException;

    /**
     * Merge.
     * 
     * @param <E> the element type
     * @param listEntity the list entity
     * @throws DaoException the dao exception
     */
    <E extends Identifiable<?>> void merge(List<E> listEntity) throws DaoException;

    // =====================================================================
    // FIND
    // =====================================================================

    /**
     * Find all.
     * 
     * @param <E> the element type
     * @param type the type
     * @return the list
     * @throws DaoException the dao exception
     */
    <E extends Identifiable<?>> List<E> findAll(Class<E> type) throws DaoException;

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
    <E extends Identifiable<?>> List<E> find(Class<E> type, final int first, final int pageSize, final String field,
                                             final String sortOrder, Map<String, String> filters) throws DaoException;

    // =====================================================================
    // COUNT
    // =====================================================================
    /**
     * Count.
     * 
     * @param type the type
     * @return the int
     * @throws DaoException the dao exception
     */
    int count(final Class<? extends Identifiable<? extends Serializable>> type) throws DaoException;

    /**
     * Count.
     * 
     * @param filters the filters
     * @param type the type
     * @return the int
     * @throws DaoException the dao exception
     */
    int count(Map<String, String> filters, final Class<? extends Identifiable<? extends Serializable>> type)
            throws DaoException;

    // =====================================================================
    // DELETE
    // =====================================================================
    /**
     * Delete.
     * 
     * @param <E> the element type
     * @param <PK> the generic type
     * @param entity the entity
     * @throws DaoException the dao exception
     */
    <E extends Identifiable<PK>, PK extends Serializable> void delete(E entity) throws DaoException;

    /**
     * Delete.
     * 
     * @param <E> the element type
     * @param <PK> the generic type
     * @param uid the uid
     * @param type the type
     * @throws DaoException the dao exception
     */
    <E extends Identifiable<PK>, PK extends Serializable> void delete(PK uid, final Class<E> type) throws DaoException;

}