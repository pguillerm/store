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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.foobar.store.commons.exceptions.DaoException;
import org.foobar.store.commons.exceptions.DaoExceptionNullEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DaoGenericJpa
 * 
 * @author pguillerm
 * @since 13 févr. 2015
 */
@Named
public class DaoGenericJpa implements Dao {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long        serialVersionUID = 1707662353016586543L;

    /** The Constant LOGGER. */
    private static final Logger      LOGGER           = LoggerFactory.getLogger(Class.class);

    protected static final String    SQL_COUNT_BEGIN  = "SELECT COUNT(e.id) ";

    protected static final String    SQL_COUNT        = SQL_COUNT_BEGIN + " FROM %s e";

    protected static final Pattern   REGEX_INJECT     = Pattern.compile("([\'\\-;=\\?$/]+)|([<>])");

    @Inject
    private DaoEntityManagerProducer daoEntityManagerProducer;

    private EntityManager            entityManager;

    private UserTransaction          transaction;

    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================
    @PostConstruct
    public void init() {
        this.entityManager = daoEntityManagerProducer.getEntityManager();
        this.transaction = daoEntityManagerProducer.getTransaction();
    }

    // =========================================================================
    // IMPLEMENTATION
    // =========================================================================

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // GET
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends Identifiable<PK>, PK extends Serializable> E getByUid(final Class<E> type, final PK uid)
            throws DaoException {
        LOGGER.debug("getByUid");
        final E entity = getEntityInstance(type);
        entity.setUid(uid);
        return get(entity);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends Identifiable<?>> E get(final E entity) throws DaoException {
        LOGGER.debug("get entity");
        E result = null;

        Serializable id = null;
        if (entity != null) {
            id = entity.getUid();
        }

        if (id != null) {
            result = (E) entityManager.find(entity.getClass(), id);
            if (result == null) {
                LOGGER.warn("get returned null with pk={}", id);
            }
        }
        return result;
    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // REFRESH
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends Identifiable<?>> void refresh(final E entity) throws DaoException {
        LOGGER.debug("refresh");
        if (entityManager.contains(entity)) {
            txBegin();
            entityManager.refresh(entity);
            txEnd();
        }
    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // SAVE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends Identifiable<?>> void save(final E entity) throws DaoException {
        LOGGER.debug("save entity");
        checkIfObjectNull(entity);

        txBegin();
        entityManager.persist(entity);
        txEnd();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends Identifiable<?>> void save(final List<E> listEntity) throws DaoException {
        LOGGER.debug("save list entities");
        checkIfObjectNull(listEntity);
        txBegin();
        for (final E entity : listEntity) {
            checkIfObjectNull(entity);
            entityManager.persist(entity);
        }
        txEnd();

    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // MERGE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends Identifiable<?>> E merge(final E entity) throws DaoException {
        LOGGER.debug("merge entity");
        checkIfObjectNull(entity);
        txBegin();
        final E result = entityManager.merge(entity);
        txEnd();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends Identifiable<?>> void merge(final List<E> listEntity) throws DaoException {
        LOGGER.debug("merge list entities");
        txBegin();
        for (final E entity : listEntity) {
            entityManager.merge(entity);
        }
        txEnd();

    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // FIND
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends Identifiable<?>> List<E> findAll(final Class<E> type) throws DaoException {
        LOGGER.debug("find all");
        final Query query = entityManager.createQuery("from " + type.getName());

        List<E> result = null;
        if (query != null) {
            result = query.getResultList();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends Identifiable<?>> List<E> find(final Class<E> type, final int first, final int pageSize,
                                                    final String field, final String sortOrder,
                                                    final Map<String, String> filters) throws DaoException {
        final Object[] params = { type, first, pageSize, field, sortOrder, filters };
        LOGGER.debug("find (type:{}|first:{}|pageSize:{}|field:{}|sortOrder:{}|filters:{})", params);

        final String sortField = getSortField(field);
        final Query query = entityManager.createQuery(queryFind(sortField, sortOrder, filters, type));
        query.setFirstResult(first);
        query.setMaxResults(first + pageSize);
        return query.getResultList();
    }

    /**
     * Gets the sort field.
     * 
     * @param field the field
     * @return the sort field
     */
    protected String getSortField(final String field) {
        String sortField = "uid";
        if (field != null) {
            sortField = field;
        }
        return sortField;
    }

    /**
     * Query find.
     * 
     * @param sortField the sort field
     * @param sortOrder the sort order
     * @param filters the filters
     * @param type the type
     * @return the string
     * @throws DaoException the dao exception
     */
    protected String queryFind(final String sortField, final String sortOrder, final Map<String, String> filters,
                               final Class<?> type) throws DaoException {
        final StringBuilder sql = new StringBuilder();
        sql.append(queryFindCriteria(filters, type));

        sql.append(" ORDER BY  ");
        sql.append("e." + secureQuery(sortField) + " ");
        sql.append(secureQuery(sortOrder));
        return sql.toString();
    }

    /**
     * Query find criteria.
     * 
     * @param filters the filters
     * @param type the type
     * @return the string
     * @throws DaoException the dao exception
     */
    private String queryFindCriteria(final Map<String, String> filters, final Class<?> type) throws DaoException {
        final StringBuilder sql = new StringBuilder();
        sql.append("FROM ");
        sql.append(type.getName());
        sql.append(" e ");

        if (filters != null && !filters.isEmpty()) {
            sql.append(" WHERE ");

            final Iterator<String> iterator = filters.keySet().iterator();

            while (iterator.hasNext()) {
                final String key = iterator.next();
                sql.append(" e." + key);
                sql.append(" like ");
                sql.append(" '%");
                sql.append(secureQuery(filters.get(key)));
                sql.append("%'");

                if (iterator.hasNext()) {
                    sql.append(" AND ");
                }
            }
        }
        return sql.toString();
    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // COUNT
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * {@inheritDoc}
     */
    @Override
    public int count(final Class<? extends Identifiable<? extends Serializable>> type) throws DaoException {
        LOGGER.debug("count");
        return count(null, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int count(final Map<String, String> filters, final Class<? extends Identifiable<? extends Serializable>> type)
            throws DaoException {
        LOGGER.debug("count(filters:{})", filters);
        int result = 0;
        txBegin();

        Query query = null;
        if (filters == null) {
            query = entityManager.createQuery(String.format(SQL_COUNT, type.getName()));
        } else {
            query = entityManager.createQuery(SQL_COUNT_BEGIN + queryFindCriteria(filters, type));
        }
        final Number countResult = (Number) query.getSingleResult();
        result = countResult.intValue();

        txEnd();

        return result;
    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // DELETE
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends Identifiable<PK>, PK extends Serializable> void delete(final E entity) throws DaoException {
        LOGGER.debug("delete");
        if (entityManager.contains(entity)) {
            txBegin();
            entityManager.remove(entity);
            txEnd();
        } else {
            // could be a delete on a transient instance
            final E entityRef = (E) entityManager.getReference(entity.getClass(), entity.getUid());

            if (entityRef != null) {
                txBegin();
                entityManager.remove(entityRef);
                txEnd();
            } else {
                LOGGER.warn("Attempt to delete an instance that is not present in the database: {}", entity.toString());
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends Identifiable<PK>, PK extends Serializable> void delete(final PK uid, final Class<E> type)
            throws DaoException {
        LOGGER.debug("delete(uid:{}", uid);
        final Identifiable<PK> entity = getByUid(type, uid);
        if (entity != null) {
            delete(entity);
        }
    }

    // =========================================================================
    // UTILS
    // =========================================================================
    /**
     * Allow to start transaction.
     * 
     * @throws DaoException the dao exception
     */
    protected void txBegin() throws DaoException {
        if (transaction != null) {
            try {
                transaction.begin();
            } catch (NotSupportedException | SystemException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }
    }

    /**
     * Allow to commit current transaction.
     * 
     * @throws DaoException the dao exception
     */
    protected void txEnd() throws DaoException {
        if (transaction != null) {

            try {
                transaction.commit();
            } catch (SecurityException | IllegalStateException | HeuristicMixedException | SystemException stateException) {
                throw new DaoException(stateException.getMessage(), stateException);
            } catch (RollbackException | HeuristicRollbackException rollBackException) {
                try {
                    transaction.rollback();
                } catch (IllegalStateException | SecurityException | SystemException exceptInRollBack) {
                    throw new DaoException(exceptInRollBack.getMessage(), exceptInRollBack);
                }
            }
        }

    }

    /**
     * Secure query.
     * 
     * @param value the value
     * @return the string
     * @throws DaoException the dao exception
     */
    protected String secureQuery(final String value) throws DaoException {
        if (value == null) {
            return "";
        } else {
            final Matcher matcher = REGEX_INJECT.matcher(value);
            if (matcher.matches() || value.contains("'") || value.contains("\\")) {
                throw new DaoException("invalide query! (" + value + ")");
            }
        }
        return value.trim();
    }

    /**
     * Check if object null.
     * 
     * @param object the object
     * @throws DaoExceptionNullEntity the dao entity null exception
     */
    protected void checkIfObjectNull(final Object object) throws DaoException {
        if (object == null) {
            throw new DaoExceptionNullEntity();
        }
    }

    /**
     * Allow to get a new entity instance
     * 
     * @param <E> the entity type
     * @param clazz the entity clazz
     * @return instance of entity
     * @throws DaoException if clazz parameter isn't same than type E
     */
    @SuppressWarnings("unchecked")
    protected <E extends Identifiable<?>> E getEntityInstance(final Class<?> clazz) throws DaoException {
        E result = null;

        try {
            result = (E) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.error(e.getMessage(), e);
            } else {
                LOGGER.error(e.getMessage());

            }
            throw new DaoException(e.getMessage(), e);
        }
        return result;
    }
}
