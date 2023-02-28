#!/usr/bin/env bash

python3 -m grpc_tools.protoc -I. --python_out=../robinhood --pyi_out=../robinhood --grpc_python_out=../robinhood robinhood.proto

protoc -I. \
    --java_out=../../Robinhood-grpc-service-api/src/main/java \
    --plugin=protoc-gen-grpc-java=/usr/local/grpc-java/bin/protoc-gen-grpc-java \
    --grpc-java_out=../../Robinhood-grpc-service-api/src/main/java \
    robinhood.proto