FROM openjdk:8-jdk-alpine

VOLUME /a402

ENV AWS_ACCESS_KEY_ID $AWS_ACCESS_KEY_ID

ENV AWS_SECRET_ACCESS_KEY $AWS_SECRET_ACCESS_KEY

COPY backend/file-service/build/libs/*.jar file-service.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "file-service.jar"]
