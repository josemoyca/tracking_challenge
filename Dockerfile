FROM eclipse-temurin:17-jdk-jammy
LABEL authors="jmoyano@intelix.ad"
# Set the working directory
WORKDIR /app
# Copy the JAR file to the container
COPY target/*.jar app.jar
# Define the command to run your application
CMD ["java", "-jar", "app.jar"]