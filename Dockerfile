FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY . /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests
ENTRYPOINT ["java","-jar","/app/target/demo-0.0.1-SNAPSHOT.jar"]
