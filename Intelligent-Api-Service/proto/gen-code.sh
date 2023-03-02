#!/usr/bin/env bash

python3 -m grpc_tools.protoc -I. --python_out=../intelligent --pyi_out=../intelligent --grpc_python_out=../intelligent intelligent.proto
