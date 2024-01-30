
#
#FROM maven:3-openjdk AS build
#COPY..
#RUN mvn clean package -DskipTests
#
#
#FROM openjdk:17-ea-28-jdk-slim-buster
#COPY --from=build /target/ecommerce-0.0.1-SNAPSHOT.jar ecommerce.jar
#EXPOSE 8080
#ENTRYPOINT["java","-jar","ecommerce.jar"]

#
# Build stage
#
#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
#FROM openjdk:11-jre-slim
#COPY --from=build /home/app/target/e-commerce-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]