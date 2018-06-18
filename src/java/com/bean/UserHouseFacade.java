/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author greit
 */
@Stateless
public class UserHouseFacade extends AbstractFacade<UserHouse> {

    @PersistenceContext(unitName = "VHPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserHouseFacade() {
        super(UserHouse.class);
    }
    
}
