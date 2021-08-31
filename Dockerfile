FROM adoptopenjdk/openjdk14
RUN addgroup -system spring && adduser spring -ingroup spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Xmx512m","-jar","/app.jar"]