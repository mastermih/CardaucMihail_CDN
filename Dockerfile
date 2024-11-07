FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y curl

WORKDIR /app


COPY target/cdn-service-0.0.1-SNAPSHOT.jar /app/cdn-service-0.0.1-SNAPSHOT.jar

# Copy the copy script into the container
COPY copy-images.sh /app/copy-images.sh

# Make the script executable
RUN chmod +x /app/copy-images.sh
EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app/cdn-service-0.0.1-SNAPSHOT.jar"]
