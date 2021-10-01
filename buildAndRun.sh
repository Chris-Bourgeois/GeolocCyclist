#!/bin/sh
mvn clean package && docker build -t com.mycompany/GeolocCyclist .
docker rm -f GeolocCyclist || true && docker run -d -p 9080:9080 -p 9443:9443 --name GeolocCyclist com.mycompany/GeolocCyclist