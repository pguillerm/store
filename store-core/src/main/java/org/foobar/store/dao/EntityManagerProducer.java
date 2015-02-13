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
package org.foobar.store.dao;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.foobar.store.commons.dao.DaoEntityManagerProducer;

/**
 * UsageEntityManagerProducer
 * 
 * @author pguillerm
 * @since 1 févr. 2015
 */
@Named
@ApplicationScoped
public class EntityManagerProducer implements DaoEntityManagerProducer {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3977075838343396134L;

    /** The transaction. */
    @Resource
    private UserTransaction   transaction;

    /** The entity manager. */
    @PersistenceContext
    private EntityManager     entityManager;

    // =========================================================================
    // METHODS
    // =========================================================================
    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public UserTransaction getTransaction() {
        return transaction;
    }
}
