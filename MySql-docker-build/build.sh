#!/usr/bin/env bash

docker build -t mysql-image .

docker run -p 3306:3306 --name mysql-container -d mysql-image