FROM maven:3.8.7-eclipse-temurin-19 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:19-jdk
WORKDIR /app
COPY --from=build /target/cmpt276assign2-1.0-SNAPSHOT.jar cmpt276assign2.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cmpt276assign2.jar"]