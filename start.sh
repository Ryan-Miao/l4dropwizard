#!/bin/sh

sh stop.sh

rm -f tpid

nohup  java -jar target/l4dropwizard-1.0.jar server target/classes/config/dev.yml > /dev/null 2>&1 &

echo $! > tpid

echo Start Success!