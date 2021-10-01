/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stema.managedbeans;

import com.stema.beans.Activity;
import com.stema.models.ActivityModel;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.bson.types.ObjectId;

/**
 *
 * @author bourg
 */
@Named(value = "activityManagedBean")
@Dependent
public class ActivityManagedBean {

    private Activity activity = new Activity();
    private List<Activity> activities = new ArrayList<>();
    
    @EJB
    private ActivityModel activityModel;
      
    public ActivityManagedBean() {
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
    
    public void delete(ObjectId idActivity)
    {
        activityModel.delete(idActivity);
    }
}
