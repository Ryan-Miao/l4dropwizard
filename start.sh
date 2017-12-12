#!/bin/sh

sh stop.sh

rm -f tpid

nohup  java -jar bing-1.0.jar server dev.yml > /dev/null 2>&1 &

echo $! > tpid

echo Start Success!