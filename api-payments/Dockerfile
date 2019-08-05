FROM openjdk:8-jre-alpine

LABEL maintainer="Lenilson Teixeira <lenilsonts@gmail.com>"

ADD target/api-payments-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]