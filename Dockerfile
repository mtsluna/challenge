FROM amazoncorretto:17 as builder

WORKDIR /app
COPY . .
ENV GRADLE_USER_HOME=`pwd`/.gradle
ENV GRADLE_OPTS="-Dorg.gradle.daemon=false"
RUN chmod +x ./gradlew
RUN ./gradlew assemble

FROM amazoncorretto:17.0.1-alpine3.14
ENV SPRING_PROFILES_ACTIVE=pod

COPY --from=builder app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]