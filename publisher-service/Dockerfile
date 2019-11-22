FROM openjdk:8
ADD /target/publisher-service-0.0.1-SNAPSHOT.jar publisher-service.jar
EXPOSE 4564
ENTRYPOINT ["java", "-jar", "publisher-service.jar"]