FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/file-service/build/libs/*.jar file-service.jar

ENTRYPOINT ["java", "-jar", "file-service.jar"]
