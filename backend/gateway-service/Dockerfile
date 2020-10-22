FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY build/libs/*.jar gateway-service.jar

ENTRYPOINT ["java", "-jar", "gateway-service.jar"]
