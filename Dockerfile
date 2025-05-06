FROM node:22.15-alpine3.21 AS frontend-builder
WORKDIR /app
COPY /zenspent-ui/package*.json ./
RUN npm install
COPY /zenspent-ui ./
RUN npm run build

FROM maven:3.9.9-eclipse-temurin-21-alpine AS backend-builder
WORKDIR /app
COPY /zenspent ./
RUN mvn clean package -DskipTests
COPY --from=frontend-builder /app/frontend /app/build/resources/main/static

FROM eclipse-temurin:21.0.7_6-jre-alpine-3.21
COPY --from=backend-builder /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
