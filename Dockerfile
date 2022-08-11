FROM maven:3.6.1-alpine as builder
RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN ["mvn", "clean", "install", "-Dmaven.test.skip=true"]

FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=builder /app/source/target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]