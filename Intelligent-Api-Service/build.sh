#!/usr/bin/env bash

docker build -t intelligent-python-sdk-image .
docker run -p 7911:7911 intelligent-python-sdk-image python3 /app/intelligent/intelligent-service.py