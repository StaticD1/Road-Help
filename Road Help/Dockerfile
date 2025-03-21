# Этап сборки приложения
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app

# Копируем исходный код приложения
COPY src /app/src
COPY pom.xml /app
RUN mvn dependency:go-offline

# Загружаем зависимости и собираем приложение
RUN mvn clean package -DskipTests

# Этап выполнения приложения
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Копируем собранные файлы из предыдущего этапа
COPY --from=build /app/target/*.jar app.jar

# Порт приложения
EXPOSE 8080

# Запускаем приложение при запуске контейнера
ENTRYPOINT ["java", "-jar", "app.jar"]
