FROM openjdk:17-oracle
MAINTAINER pengbin
VOLUME /tmp
ADD docker-boot-1.0-SNAPSHOT.jar pengbin_docker.jar
RUN bash -c 'touch /pengbin_docker.jar'
ENTRYPOINT ["java","-jar","/pengbin_docker.jar"]
EXPOSE 8001