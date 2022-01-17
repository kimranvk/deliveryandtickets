FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/delivery-1.0.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT java -jar /app.jar --jedis.host="192.168.0.143" --spring.profiles.active="dev"