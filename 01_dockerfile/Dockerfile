#FROM image
#ADD /my/Test.java /a/b/Test.java
#RUN app-get install java
#ENV JAVA_HOME=/path
#WORKDIR /a/b
#EXPOSE 8050
#ENTRYPOINT sleep 5

FROM alpine
RUN apk add openjdk21-jdk
#ENV PATH $PATH:/usr/lib/jvm/java-21-openjdk/bin
WORKDIR /usr/workspace
ADD Welcome.java Welcome.java
ENTRYPOINT javac Welcome.java && java Welcome $NAME