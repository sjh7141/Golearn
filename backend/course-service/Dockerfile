FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/course-service/build/libs/*.jar course-service.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "course-service.jar"]
