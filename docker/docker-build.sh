#!/bin/bash
export PROJECTS_PATH="$(dirname "$(pwd)")"

cd $PROJECTS_PATH/docker

printf "\n> Building image: MS-ORDERS \n"
mvn -f ../ms-orders/pom.xml package dockerfile:build -DskipTests
