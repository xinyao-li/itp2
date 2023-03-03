#!/usr/bin/env bash

mvn clean clean package

docker build -t stock-java-sdk-image .

docker run -p 8082:8082 stock-java-sdk-image