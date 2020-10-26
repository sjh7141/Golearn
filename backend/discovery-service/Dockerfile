FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/discovery-service/build/libs/*.jar discovery-service.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "discovery-service.jar"]
