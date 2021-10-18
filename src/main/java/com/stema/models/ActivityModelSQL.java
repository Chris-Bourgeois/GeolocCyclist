/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stema.models;

import com.stema.beans.Activity;
import java.time.LocalDate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bourg
 */
@Stateless
public class ActivityModelSQL {
    
    @PersistenceContext
    private EntityManager em;

    public ActivityModelSQL() {
    }
    
    public void create(Activity activity) {
        em.persist(activity);
    }
    
    public void stop(Activity finishedActivity)
    {
        Activity activity = em.find(Activity.class, finishedActivity.getId());
        
        activity.setStopTime(LocalDate.now());    
    }
    
    public void delete(Activity activity){
        activity = em.merge(activity);
        em.remove(activity);
    }
}
