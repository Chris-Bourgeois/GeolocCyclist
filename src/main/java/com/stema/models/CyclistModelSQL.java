/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stema.models;

import com.stema.beans.Cyclist;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bourg
 */
@Stateless
public class CyclistModelSQL {

    @PersistenceContext
    private EntityManager em;

    public CyclistModelSQL() {
    }
    
    public void create(Cyclist cyclist) {
        em.persist(cyclist);
    }
    
    public void update(Cyclist newCyclist)
    {
        newCyclist = em.merge(newCyclist);
        em.refresh(newCyclist);
    }
    
    public void delete(Cyclist cyclist){
        cyclist = em.merge(cyclist);
        em.remove(cyclist);
    }
}
