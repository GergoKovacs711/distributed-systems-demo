FROM openjdk:8
ADD target/distributed-systems-demo.jar distributed-systems-demo.jar
EXPOSE 8095
ENTRYPOINT ["java", "-jar", "distributed-systems-demo.jar"]