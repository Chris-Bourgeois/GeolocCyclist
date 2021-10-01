/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stema.models;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.stema.beans.Activity;
import com.stema.beans.Cyclist;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

/**
 *
 * @author bourg
 */
@Stateless
public class ActivityModel {
    
    @EJB
    MongoClientProvider mongoClientProvider;
    
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Activity> collection;
    
    @PostConstruct
    public void init() {
        mongoClient = mongoClientProvider.getMongoClient();
        
        CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
                
        mongoDatabase = mongoClient.getDatabase("GeolocCyclist").withCodecRegistry(codecRegistry);
        collection = mongoDatabase.getCollection("activity", Activity.class);
    }
    
    /**
     * fonction générique pour afficher toutes les activités
     * @return
     */
    public List<Activity> read() {
        List<Activity> liste = new ArrayList<>();
        collection.find().iterator().forEachRemaining(liste::add);
        return liste;
    }
    
    /**
     * fonction générique pour supprimer une activité
     * @param id 
     */
    public void delete(ObjectId id){
        Document document = new Document("_id", id);
        collection.deleteOne(document);
    }
    
    public Activity find(ObjectId id)
    {
        Activity activity = (Activity) collection.find(eq("_id", id)).first();
        return activity;
    }
}
