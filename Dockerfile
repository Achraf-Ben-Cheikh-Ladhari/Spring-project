FROM openjdk:17-jdk

WORKDIR /app

COPY target/ladhari-0.0.1-SNAPSHOT.jar /app/springproject.jar

EXPOSE 8080

CMD ["java","-jar","springproject.jar"]