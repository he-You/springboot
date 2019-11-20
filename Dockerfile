FROM java:8
VOLUME /tmp
ADD springboot-0.0.1-SNAPSHOT.jar /springboot-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springboot-0.0.1-SNAPSHOT.jar"]
