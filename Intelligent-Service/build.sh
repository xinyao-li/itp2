#!/usr/bin/env bash

docker build -t intelligent-python-service-image .

docker run -p 8084:8084 intelligent-python-service-image python3 /app/intelligent/intelligentController.py