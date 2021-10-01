/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stema.managedbeans;

import com.stema.beans.Cyclist;
import com.stema.models.CyclistModel;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author bourg
 */
@Named(value = "cyclistMapManagedBean")
@SessionScoped
public class CyclistMapManagedBean implements Serializable{
    
    private Cyclist cyclist = new Cyclist();
    private MapModel simpleModel;
    
    @EJB
    CyclistModel cyclistModel;
    
    public CyclistMapManagedBean() {
    }

    public Cyclist getCyclist() {
        return cyclist;
    }

    public void setCyclist(Cyclist cyclist) {
        this.cyclist = cyclist;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }
    
    public void map(Cyclist cyclist){
        this.cyclist = cyclist;        
        this.cyclist = cyclistModel.find(this.cyclist.getId());
        
        LatLng coord = new LatLng(Float.parseFloat(this.cyclist.getLatitude()), Float.parseFloat(this.cyclist.getLongitude()));
        simpleModel = new DefaultMapModel();
        
        Marker marker=new Marker(coord, "Cyclist", this, "mapMarker.png");  
        simpleModel.addOverlay(marker);
                
        //return "map.jsf";
    }
}
