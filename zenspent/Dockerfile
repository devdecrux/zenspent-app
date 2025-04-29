FROM node:22.15-alpine3.21 AS frontend-builder
WORKDIR /app
COPY ../zenspent-ui/package*.json ./
RUN npm install
COPY ../zenspent-ui ./
RUN npm run build
#FROM eclipse-temurin:21.0.7_6-jre-alpine-3.21
#WORKDIR /app
#COPY . ./
#RUN mvn package -DskipTests
#COPY --from=frontend-builder /app/frontend /app/build/resources/main/static
#
#RUN ls -la /app/build/resources/main/static
#
#COPY target/*.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]
