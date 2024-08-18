    # Use an official OpenJDK runtime as a parent image
    FROM openjdk:11-jre-slim

    # Set the working directory
    WORKDIR /app

    # Copy the JAR file to the container
    COPY discovery_LMS/target/*.jar app.jar

    # Expose the port your application runs on
    EXPOSE 8080

    # Run the JAR file
    CMD ["java", "-jar", "app.jar"]