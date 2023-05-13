FROM openjdk:17-jdk-alpine
COPY target/microMapBack-0.0.1-SNAPSHOT.jar app.jar
ENV NEO4J_URI=uri
ENV NEO4J_USER=user
ENV NEO4J_PASS=pass
ENV PORT=8081
EXPOSE $PORT


ENTRYPOINT ["java","-jar","app.jar"]
