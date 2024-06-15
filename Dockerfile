FROM maven:3.8.7-openjdk-19-slim AS build
COPY . .
RUN mvn clean package -DskipTests

FROM adoptopenjdk:19-jdk
COPY --from=build /target/cmpt276assign2-1.0-SNAPSHOT.jar cmpt276assign2.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cmpt276assign2.jar"]
