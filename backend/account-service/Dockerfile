FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/account-service/build/libs/*.jar account-service.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "account-service.jar"]
