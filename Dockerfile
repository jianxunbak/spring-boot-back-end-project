# Use a base image with Maven preinstalled
FROM maven:3.8.4-openjdk-11-slim

# Set the working directory in the container
WORKDIR /app

# Copy your pom.xml and source code to the container
COPY . .

# Run the Maven build command
RUN mvn clean package

# Expose the port the app runs on
EXPOSE 8080

# Run the JAR file when the container starts
CMD ["java", "-jar", "target/*.jar"]