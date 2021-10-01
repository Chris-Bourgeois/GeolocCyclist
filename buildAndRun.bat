@echo off
call mvn clean package
call docker build -t com.mycompany/GeolocCyclist .
call docker rm -f GeolocCyclist
call docker run -d -p 9080:9080 -p 9443:9443 --name GeolocCyclist com.mycompany/GeolocCyclist