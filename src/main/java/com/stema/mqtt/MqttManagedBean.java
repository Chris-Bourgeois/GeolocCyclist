/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stema.mqtt;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author bourg
 */
@Named(value = "mqttManagedBean")
@RequestScoped
public class MqttManagedBean {

    MqttClient client;
    
    private String text;
    
    public MqttManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        try {
            client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId(), new MemoryPersistence());

            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setConnectionTimeout(300);
            connOpts.setKeepAliveInterval(30);
            connOpts.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
            client.connect(connOpts);           

        } catch (IllegalArgumentException | MqttException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void publishMessage() {
        try {

            MqttMessage message = new MqttMessage(text.getBytes());
            client.publish("topic/cyclist", message);

            text = "";
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    } 
}
