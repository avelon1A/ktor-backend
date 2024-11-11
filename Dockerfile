# Use an official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle build files
COPY gradle /app/gradle
COPY gradlew /app
COPY build.gradle.kts /app
COPY settings.gradle.kts /app

# Copy the Ktor application's source code
COPY src /app/src

# Make the Gradle wrapper executable

# Build the application
RUN ./gradlew build

# Copy the generated JAR file into the container
COPY build/libs/testcoverage-all.jar /app/testcoverage-all.jar

# Expose the port that the Ktor app will be running on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "/app/testcoverage-all.jar"]
