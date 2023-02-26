#!/usr/bin/env bash

python3 -m grpc_tools.protoc -I. --python_out=../robinhood --pyi_out=../robinhood --grpc_python_out=../robinhood robinhood.proto

protoc -I. --java_out=../../robinhood-grpc-service-api/src/main/java robinhood.proto
#java -m grpc_tools.protoc -I. --java_out=./../robinhood-grpc-service-api/src/main/java --grpc_java_out=./../robinhood-grpc-service-api/src/main/java robinhood.proto

protoc -I. \
    --java_out=../../robinhood-grpc-service-api/src/main/java \
    --plugin=protoc-gen-grpc-java=../../robinhood-grpc-service-api/src/main/java \
    --grpc-java_out=../../robinhood-grpc-service-api/src/main/java \
    robinhood.proto