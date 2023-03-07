#!/usr/bin/env bash

python3 -m grpc_tools.protoc -I. --python_out=../robinhood --pyi_out=../robinhood --grpc_python_out=../robinhood robinhood.proto

