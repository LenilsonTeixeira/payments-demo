#!/bin/bash
export PROJECTS_PATH="$(dirname "$(pwd)")"

cd $PROJECTS_PATH/docker

printf "\n> Building image: MS-Orders \n"
mvn -f ../ms-orders/pom.xml package dockerfile:build -DskipTests

printf "\n> Building image: API-Payments \n"
mvn -f ../api-payments/pom.xml package dockerfile:build -DskipTests
