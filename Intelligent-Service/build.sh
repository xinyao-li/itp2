#!/usr/bin/env bash

mvn clean clean package

docker build -t intelligent-java-sdk-image .

docker run -p 8084:8084 intelligent-java-sdk-image --mysql.address=192.168.1.67 --intelligent.address=192.168.1.67
