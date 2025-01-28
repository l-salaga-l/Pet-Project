FROM mirror.gcr.io/openjdk:17-alpine

WORKDIR /app

COPY target/*SNAPSHOT.war ./app.war

EXPOSE 9019

ENTRYPOINT ["java", "-jar", "/app/app.war"]