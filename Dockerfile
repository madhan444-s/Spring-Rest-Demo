# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar file into the container at /app
COPY target/demo-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Set environment variables for database connection
#using the host.docker.internal instead of localhost to connect to a locally host db
ENV SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/sample
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=Admin@123
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver

# Run the jar file
ENTRYPOINT ["java", "-jar", "myapp.jar"]
