#!/usr/bin/env bash

docker build -t robinhood-python-sdk-image .
docker run -p 7911:7911 robinhood-python-sdk-image python3 /app/robinhood/robinhood-service.py