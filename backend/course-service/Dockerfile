FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/course-service/build/libs/*.jar course-service.jar

ENTRYPOINT ["java", "-jar", "course-service.jar"]
