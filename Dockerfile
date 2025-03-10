# **Stage 1: Build the JAR file using Maven**
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml ./
COPY src ./src/

# Build the application (this will download dependencies and compile the code)
RUN mvn clean package -DskipTests

# **Stage 2: Run the JAR file using OpenJDK**
FROM openjdk:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/group3-assignment-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on (change if needed)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]