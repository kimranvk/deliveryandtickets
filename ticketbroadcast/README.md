# ticketsapp

http://localhost:8081/broadcast/tickets

JDK
===
1.8

OpenAPI (download yaml)
=======================
http://localhost:8081/broadcast/v3/api-docs.yaml

Run from terminal
=================
java -jar tickets-1.0.jar --jedis.host=192.168.0.143 --spring.profiles.active=dev

restart radis service
=====================

sudo service redis-server restart


Run from Docker
===============
cd to the main folder that contains Dockerfile and execute below commands:

docker build . --tag tickets-app

docker run -it -p8081:8081 tickets-app:latest 

Authentication
==============
admin/admin
