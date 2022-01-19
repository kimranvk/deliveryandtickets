# deliveryapp

ENDPOINTS
==========
http://localhost:8080/app/cache

http://localhost:8080/app/h2console

SAMPLE DB ROWS
==============
insert into delivery 
values(3,10,'Loyal','Order Delivered',CURRENT_DATE(),30,null, CURRENT_DATE() )

JDK
===
1.8



OpenAPI (download yaml)
=======================

http://localhost:8080/app/v3/api-docs.yaml



Run from terminal
=================

java -jar delivery-1.0.jar --jedis.host=192.168.0.143 --spring.profiles.active=dev

restart radis service
=====================

sudo service redis-server restart

Run from Docker
===============

cd to the main folder that contains Dockerfile and execute below commands:
 

docker build . --tag delivery-app

docker run -it -p8080:8080 delivery-app:latest 

REDIS
=====
KEYS *

lrange tickets_1 0 100

sort tickets_1 alpha desc

rpush tickets_1 "asdfasffasd"

 redis-cli KEYS "tickets_*" | xargs redis-cli DEL
 redis-cli
