FROM amazoncorretto:21-alpine
WORKDIR /opt/app
COPY target/testeiglu-1.0.jar testeiglu-1.0.jar
ENTRYPOINT ["java", "-jar", "testeiglu-1.0.jar"]