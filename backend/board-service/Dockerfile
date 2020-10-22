FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/board-service/build/libs/*.jar board-service.jar

ENTRYPOINT ["java", "-jar", "board-service.jar"]
