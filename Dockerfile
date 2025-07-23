FROM openjdk:24-jdk
LABEL authors="lyovik"
WORKDIR /app
COPY /out/artifacts/AppSpring/AppSpring.jar /app/ComputerShop.jar
ENTRYPOINT ["java", "-jar", "ComputerShop.jar"]
