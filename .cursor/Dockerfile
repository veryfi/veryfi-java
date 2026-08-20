# Base image bundles Maven 3.8.5 and Eclipse Temurin JDK 16, matching the
# project's Java 16 compiler target and the JDK 16 used by the GitHub Actions CI.
# JDK 16 is required because JaCoCo 0.8.7 (used for coverage) does not support
# bytecode from newer JDKs such as 21.
FROM maven:3.8.5-eclipse-temurin-16

# git and curl are handy for source checkout and general tooling in Cloud Agents.
RUN apt-get update \
    && apt-get install -y --no-install-recommends git curl ca-certificates \
    && rm -rf /var/lib/apt/lists/*

# Run as a non-root user matching Cloud Agent conventions.
RUN groupadd -g 1000 ubuntu \
    && useradd -m -u 1000 -g 1000 -s /bin/bash ubuntu

USER ubuntu
WORKDIR /home/ubuntu
