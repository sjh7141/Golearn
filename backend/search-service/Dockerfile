FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/search-service/build/libs/*.jar search-service.jar

ENTRYPOINT ["java", "-jar", "search-service.jar"]
