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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author bourg
 */
@Stateless
public class CyclistModel {
    
    @EJB
    MongoClientProvider mongoClientProvider;
    
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Cyclist> collection;
    
    @PostConstruct
    public void init() {
        mongoClient = mongoClientProvider.getMongoClient();
        
        CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
                
        mongoDatabase = mongoClient.getDatabase("GeolocCyclist").withCodecRegistry(codecRegistry);
        collection = mongoDatabase.getCollection("cyclist", Cyclist.class);
    }
    
    /**
     * fonction générique pour créer un cycliste
     * @param cyclist 
     */
    public void create(Cyclist cyclist) {
        collection.insertOne(cyclist);
    }        
    
    /**
     * fonction générique pour afficher tous les cyclistes
     * @return
     */
    public List<Cyclist> read() {
        List<Cyclist> liste = new ArrayList<>();
        collection.find().iterator().forEachRemaining(liste::add);
        return liste;
    }
    
        /**
     * fonction générique pour modifier un cycliste
     * @param newCyclist
     */
    public void update(Cyclist newCyclist)
    {
        Bson oldCyclist = eq("_id", newCyclist.getId());
        collection.findOneAndReplace(oldCyclist, newCyclist);
    }
    
    /**
     * fonction générique pour supprimer un cycliste
     * @param cyclist
     */
    public void delete(Cyclist cyclist){
        Document document = new Document("_id", cyclist.getId());
        collection.deleteOne(document);
    }

    public Cyclist find(ObjectId id)
    {
        Cyclist cyclist = (Cyclist) collection.find(eq("_id", id)).first();
        return cyclist;
    }
}
