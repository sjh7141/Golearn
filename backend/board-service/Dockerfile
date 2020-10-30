FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/board-service/build/libs/*.jar board-service.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "board-service.jar"]
