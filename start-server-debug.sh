#!/bin/sh
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,address="8000" -jar ./target/FlightRoute.jar