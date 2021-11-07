FROM alpine:latest

#Installs Java8:
RUN apk --update --no-cache add openjdk8-jre nss

COPY target/article-0.0.1-SNAPSHOT.jar /article/
COPY entrypoint.sh /article/

WORKDIR /article/
ENTRYPOINT ["sh", "./entrypoint.sh"]