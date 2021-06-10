# Stage 1, build the container
ARG VERSION=8u181
FROM openjdk:${VERSION}-jdk-alpine3.8 as BUILD

# Get gradle distribution
COPY *.gradle gradlew /home/docker/code/
COPY gradle /home/docker/code/gradle
COPY gradle.properties /home/docker/code
WORKDIR /home/docker/code
RUN ./gradlew --version

COPY src /home/docker/code/src
ENV MAIN_CLASS_NAME=agco.Application
RUN ./gradlew clean assemble

# Stage 2, execute the container
FROM openjdk:${VERSION}-jdk-alpine3.8
ARG FUSE_ENV

ENV FUSE_ENV=${FUSE_ENV}

RUN apk update && apk add curl curl-dev bash python
RUN curl "https://s3.amazonaws.com/aws-cli/awscli-bundle.zip" -o "awscli-bundle.zip"
RUN unzip awscli-bundle.zip
RUN ./awscli-bundle/install -i /usr/local/aws -b /usr/local/bin/aws
RUN apk add --no-cache openssl

COPY --from=BUILD /home/docker/code/build/libs/aet-dashboard-kafka-consumer.jar app.jar
COPY docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh

ENTRYPOINT ["bash", "/docker-entrypoint.sh"]