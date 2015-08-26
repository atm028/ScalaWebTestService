FROM ubuntu:15.10
MAINTAINER Timur Morozov "timur.morozov@gmail.com"
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update
RUN apt-get install -y java8-jdk
RUN apt-get install -y apt-transport-https

RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823
RUN apt-get update
RUN apt-get install -y sbt
RUN apt-get install -y scala

RUN /var/lib/dpkg/info/ca-certificates-java.postinst configure

RUN git clone https://github.com/atm028/ScalaWebTestService.git
WORKDIR /ScalaWebTestService
RUN git pull
RUN sbt assembly
RUN cp -rf ./run.sh /run.sh
RUN chmod +x /run.sh
RUN cp -rf ./target/scala-2.11/scalawebtestservice-assembly-0.1.jar /sw.jar
WORKDIR /
RUN rm -rf /ScalaWebTestService


EXPOSE 9596
CMD ["java", "-jar", "/sw.jar"]
