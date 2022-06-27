#!/usr/bin/env bash
echo 'Building the jar ...'
#mvn clean test package
mvn clean package

cd producer-service
echo 'Building producer service image ...'
docker build -t producer-service .

cd ../consumer-service
echo 'Building consumer service image ...'
docker build -t consumer-service .

cd ..

echo 'Starting stack up ...'
docker-compose up