FROM node:22.15-alpine3.21 AS frontend-builder
WORKDIR /vue-app
COPY /zenspent-ui-v2/package-lock.json .
COPY /zenspent-ui-v2/package.json .
RUN npm install
COPY /zenspent-ui-v2 .
RUN npm run build

FROM maven:3.9.9-eclipse-temurin-21-alpine AS backend-builder
WORKDIR /backend
COPY /zenspent .
COPY --from=frontend-builder /vue-app/frontend /backend/src/main/resources/static/frontend
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21.0.7_6-jre-alpine-3.21
COPY --from=backend-builder /backend/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
