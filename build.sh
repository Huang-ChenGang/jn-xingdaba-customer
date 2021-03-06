#!/usr/bin/env bash

gradle clean -x test build

docker build --no-cache -t xingdaba/xingdaba-customer .

docker tag xingdaba/xingdaba-customer hub.c.163.com/riyuexingchenace/xingdaba/xingdaba-customer:latest

docker push hub.c.163.com/riyuexingchenace/xingdaba/xingdaba-customer:latest
