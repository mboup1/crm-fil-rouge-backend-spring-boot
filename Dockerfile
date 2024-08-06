FROM openjdk:17-jdk-slim

COPY target/crm-fil-rouge-dame-0.0.1-SNAPSHOT.jar.original /app/crm-fil-rouge-dame.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "crm-fil-rouge-dame.jar"]
