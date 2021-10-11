/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stema.managedbeans;

import com.stema.beans.Cyclist;
import com.stema.models.CyclistModel;
import com.stema.models.CyclistModelSQL;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.bson.types.ObjectId;

/**
 *
 * @author bourg
 */
@Named(value = "cyclistManagedBean")
@RequestScoped
public class CyclistManagedBean {

    private Cyclist cyclist = new Cyclist();
    private List<Cyclist> cyclists = new ArrayList<>();

    //mon modele Mongo
    @EJB
    private CyclistModel cyclistModel;
    
    //mon modele SQL
    @EJB
    private CyclistModelSQL cyclistModelSQL;

    public CyclistManagedBean() {
    }

    public Cyclist getCyclist() {
        return cyclist;
    }

    public void setCyclist(Cyclist cyclist) {
        this.cyclist = cyclist;
    }

    public List<Cyclist> getCyclists() {
        cyclists = cyclistModel.read();
        return cyclists;
    }

    public void setCyclists(List<Cyclist> cyclists) {
        this.cyclists = cyclists;
    }

    public CyclistModel getCyclistModel() {
        return cyclistModel;
    }

    public void setCyclistModel(CyclistModel cyclistModel) {
        this.cyclistModel = cyclistModel;
    }

    public void edit() {
        
        if (cyclist.getId() == null) {
            //NoSQL
            cyclistModel.create(cyclist);
            //SQL
            cyclistModelSQL.create(cyclist);
            FacesMessage success_create = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout réussi de : " + cyclist.getLastName() + " " + cyclist.getFirstName(), null);
            FacesContext.getCurrentInstance().addMessage(null, success_create);
        } else {
            cyclistModel.update(cyclist);
            cyclistModelSQL.update(cyclist);
            FacesMessage success_edit = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification réussie de : " + cyclist.getLastName() + " " + cyclist.getFirstName(), null);
            FacesContext.getCurrentInstance().addMessage(null, success_edit);
        }

        cyclist = new Cyclist();
    }

    public void delete(ObjectId idCyclist) {   
        Cyclist deletedCyclist = cyclistModel.find(idCyclist);
        FacesMessage success_delete = new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression réussie de : " + deletedCyclist.getLastName() + " " + deletedCyclist.getFirstName(), null);
        FacesContext.getCurrentInstance().addMessage(null, success_delete);
        cyclistModel.delete(deletedCyclist);
        cyclistModelSQL.delete(deletedCyclist);
    }

    public void update(Cyclist after) {
        this.cyclist = after;
    }
}
