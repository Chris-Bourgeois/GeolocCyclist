/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stema.managedbeans;

import com.stema.beans.Activity;
import com.stema.models.ActivityModel;
import com.stema.models.ActivityModelSQL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author bourg
 */
@Named(value = "activityManagedBean")
@RequestScoped
public class ActivityManagedBean {

    private Activity activity = new Activity();
    private List<Activity> activities = new ArrayList<>();

    @EJB
    private ActivityModel activityModel;

    @EJB
    private ActivityModelSQL activityModelSQL;

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

    public void start() {

        activity.setStartTime(LocalDate.now());
        activityModel.create(activity);
        activityModelSQL.create(activity);
    }
    
    public void stop()
    {
        activityModelSQL.stop(activity);
        activity = new Activity();
    }

    public void delete(Activity activity) {
        FacesMessage success_delete = new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression réussie.", null);
        FacesContext.getCurrentInstance().addMessage(null, success_delete);
        activityModel.delete(activity);
        activityModelSQL.delete(activity);
    }
}
