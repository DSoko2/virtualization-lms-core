FROM openjdk:8u181-jdk

# Starting with version 1.*, SBT dropped Java 7 support
ENV SBT_VERSION=*

# Install requirement for https installation
RUN apt-get update && apt-get install apt-transport-https

# Install SBT
RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
RUN apt-get update
RUN apt-get install sbt=$SBT_VERSION
# Trigger lazy install

# Test and install lms
COPY . /opt/lms
RUN cd /opt/lms && sbt sbtVersion
# RUN cd /opt/lms && sbt test
RUN cd /opt/lms && sbt publish-local

# Make it an endless running container
RUN touch /var/.keep-running
CMD tail -f /var/.keep-running

VOLUME /var/workspace