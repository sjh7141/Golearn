FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/video-service/build/libs/*.jar video-service.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "video-service.jar"]
