FROM openjdk:21-jdk
LABEL authors="lyovik"
WORKDIR /app
COPY target/AppSpring-0.0.1-SNAPSHOT.jar /app/ComputerShop.jar
ENTRYPOINT ["java", "-jar", "ComputerShop.jar"]