FROM openjdk:17-jdk-slim
MAINTAINER nikitasudaev
COPY build/libs/CheckRunner.jar CheckRunner.jar
ENTRYPOINT ["java","-jar","/CheckRunner.jar", "1-2", "3-5", "5-6"]