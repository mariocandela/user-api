FROM maven:3.8.3-openjdk-17 as builder

RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
RUN mvn -B dependency:resolve dependency:resolve-plugins

COPY src /build/src
COPY swagger /build/swagger

RUN mvn package

FROM amazoncorretto:17-alpine-jdk
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]