#!/bin/bash
cd ../
./mvnw package
docker-compose up -d