# Use OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-ubi10-minimal as build

# Copy the JAR file from target (you need to build with Maven first)
COPY target/chicken-0.0.1-SNAPSHOT.jar chicken.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/chicken.jar"]
