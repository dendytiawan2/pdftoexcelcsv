FROM eclipse-temurin:17-jdk-alpine
ADD target/pdf.jar pdf-api.jar
ENTRYPOINT ["java","-jar","/pdf.jar"]
