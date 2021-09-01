FROM adoptopenjdk/openjdk14
RUN addgroup -system spring && adduser spring -ingroup spring
USER spring:spring
RUN mvn clean install
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} videosFlix.jar
CMD ["java","-Xmx512m","-jar","/videosFlix.jar"]