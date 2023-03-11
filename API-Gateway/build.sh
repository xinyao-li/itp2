#!/usr/bin/env bash

mvn clean clean package

docker build -t api-gateway-spring-cloud-image .

docker run -p 8080:8080 api-gateway-spring-cloud-image