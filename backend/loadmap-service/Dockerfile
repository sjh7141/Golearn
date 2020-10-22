FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/loadmap-service/build/libs/*.jar loadmap-service.jar

ENTRYPOINT ["java", "-jar", "loadmap-service.jar"]
