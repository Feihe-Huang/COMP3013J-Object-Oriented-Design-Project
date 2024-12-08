FROM openjdk:17-jdk-slim-bullseye

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

# Waiting for the init of the database
CMD ["sh","-c","sleep 15 && java -jar app.jar"]
