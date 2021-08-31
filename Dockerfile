FROM adoptopenjdk/openjdk14
RUN addgroup -system spring && adduser spring -ingroup spring
USER spring:spring
CMD mvn clean package
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} videosFlix.jar
ENTRYPOINT ["java","-Xmx512m","-jar","/app.jar"]