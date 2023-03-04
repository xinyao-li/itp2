#!/usr/bin/env bash

mvn clean clean package

docker build -t stock-java-sdk-image .

docker run -p 8082:8082 stock-java-sdk-image --mysql.address=192.168.1.67 --robinhood.address=192.168.1.67