#!/usr/bin/env bash
protoc -I. \
    --java_out=../robinhood \
    --plugin=protoc-gen-grpc-java=../robinhood \
    --grpc-java_out=../robinhood \
    robinhood.proto