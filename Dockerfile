FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /target/order-0.0.1-SNAPSHOT.jar order.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/order.jar"]