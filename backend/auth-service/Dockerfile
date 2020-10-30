FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/auth-service/build/libs/*.jar auth-service.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "auth-service.jar"]
