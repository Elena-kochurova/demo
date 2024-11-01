FROM openjdk:23-ea-17-jdk-bullseye
WORKDIR /demo
COPY /build/libs/demo-0.0.1-SNAPSHOT.jar /demo/demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]