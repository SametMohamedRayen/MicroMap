FROM openjdk:17-jdk-alpine
COPY target/microMapBack-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV NEO4J_URI=uri
ENV NEO4J_USER=user
ENV NEO4J_PASS=pass

ENTRYPOINT ["java","-jar","app.jar"]
