FROM gradle:7.5.0-jdk17
WORKDIR /opt/app
COPY ./build/libs/Semester3project-0.0.1-SNAPSHOT.jar ./
COPY wait_for_db.sh ./

RUN chmod +x wait_for_db.sh
ENTRYPOINT ["./wait_for_db.sh", "mysql:3306", "--", "sh", "-c", "java ${JAVA_OPTS} -jar Semester3project-0.0.1-SNAPSHOT.jar"]

#FROM openjdk:8-jdk-alpine
#
#ARG JAR_FILE=build/libs/*.jar
#
#
#ADD ${JAR_FILE} app.jar
#
#
#ENTRYPOINT ["java","-jar","/app.jar"]