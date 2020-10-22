FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/video-service/build/libs/*.jar video-service.jar

ENTRYPOINT ["java", "-jar", "video-service.jar"]
