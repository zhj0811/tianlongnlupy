#!/bin/bash

WORD="$1"
NACOS="$2"
if [ -n "$NACOS" ]; then
  export NACOS
fi
function stop() {
    docker stop nvsa
    docker rm nvsa
}
function start() {
    docker-compose -f ../docker/docker-compose.yml up -d
}
function status() {
    return $(docker ps | grep nvsa)
}
case $WORD in
    start)
        echo ""
        echo "Application Starting........... "
        echo ""
        start
    ;;

    stop)
        echo ""

        echo "Application Stopping.......... "

        echo ""
        stop
    ;;

    restart)
        echo "Application is Restarting.......... "
        stop
        echo ""
        start
        echo "Application is Starting.......... "

    ;;

    status)
        status>/dev/null 2>&1
        if [[ $? -eq 0 ]]; then
            echo ""
            echo "Application is not Running......"
            echo ""
        else echo "Application is Running"
        fi
     ;;

    *)
      ;;
esac
