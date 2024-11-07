#!/bin/bash

# Define source and destination directories
SOURCE_DIR="/app/src/main/resources/uploads/images"
TARGET_DIR="/app/src/main/resources/static"

# Ensure the target directory exists
mkdir -p "$TARGET_DIR"

# Copy images from source to target directory
cp -r "$SOURCE_DIR"/* "$TARGET_DIR"

# Run the application
exec java -jar /app/cdn-service-0.0.1-SNAPSHOT.jar
