#!/usr/bin/env bash
echo 'Building the jar ...'
mvn clean package

cd producer-service
echo 'Building producer service image ...'
docker build -t producer-service .

cd ../consumer-service
echo 'Building consumer service image ...'
docker build -t consumer-service .

cd ../web-socket
echo 'Building web socket image ...'
docker build -t web-socket .

#cd ../listener-service
#echo 'Building listener service image ...'
#docker build -t listener-service .

cd ..

echo 'Starting stack up ...'
docker-compose up