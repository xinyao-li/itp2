#!/usr/bin/env bash

docker build -t intelligent-api-service-image .

docker run -p 7911:7911 intelligent-api-service-image python3 /app/intelligent/intelligent-service.py