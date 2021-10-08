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
import org.bson.types.ObjectId;

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
        Cyclist cyclist = em.find(Cyclist.class, newCyclist.getId());
        
        cyclist.setEmail(newCyclist.getEmail());
        cyclist.setFirstName(newCyclist.getFirstName());
        cyclist.setLastName(newCyclist.getLastName());
        cyclist.setLatitude(newCyclist.getLatitude());
        cyclist.setLongitude(newCyclist.getLongitude());
        cyclist.setPassword(newCyclist.getPassword());
        cyclist.setPicture(newCyclist.getPicture());        
    }
    
    public void delete(Cyclist cyclist){
        cyclist = em.merge(cyclist);
        em.remove(cyclist);
    }
}
