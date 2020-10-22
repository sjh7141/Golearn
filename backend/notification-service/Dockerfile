FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/notification-service/build/libs/*.jar notification-service.jar

ENTRYPOINT ["java", "-jar", "notification-service.jar"]
