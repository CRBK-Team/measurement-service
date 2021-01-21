FROM openjdk:11-jdk
VOLUME /tmp
ARG JAR_FILE=/build/libs/crbk-project-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS="-XX:+UseG1GC -Xmx1024m"
COPY ${JAR_FILE} app.jar
COPY ./entrypoint.sh entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
