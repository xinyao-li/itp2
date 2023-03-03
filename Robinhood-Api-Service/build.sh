#!/usr/bin/env bash

docker build -t robinhood-api-service-image .

docker run -p 9090:9090 robinhood-api-service-image python3 /app/robinhood/robinhood-service.py