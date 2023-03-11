#!/usr/bin/env bash

mvn clean clean package

docker build -t api-gateway-zuul-image .

docker run -p 8080:8080 api-gateway-zuul-image --local.address=192.168.1.67