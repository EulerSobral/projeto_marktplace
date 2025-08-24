FROM openjdk:21-jdk-slim
WORKDIR /projetct_marktplace
COPY build/libs/projetct_marktplace-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]