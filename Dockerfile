# define base docker image
FROM  openjdk:11
LABEL maintainers = "iseb.com.br"
ADD target/iesb-1.0.0-BUILD-SNAPSHOT.jar springboot-docker-demo.jar
ENTRYPOINT ["java", "-jar", "springboot-docker-demo.jar"]