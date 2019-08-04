#!/bin/bash
export PROJECTS_PATH="$(dirname "$(pwd)")"

cd $PROJECTS_PATH/docker

printf "\n> Building image: MS-Orders \n"
mvn -f ../ms-orders/pom.xml package dockerfile:build -DskipTests

printf "\n> Building image: API-Products \n"
mvn -f ../api-products/pom.xml package dockerfile:build -DskipTests
