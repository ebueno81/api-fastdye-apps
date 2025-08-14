# ========= STAGE 1: Build =========
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /src

# Copia y “calienta” caché de dependencias
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Copia el código y compila
COPY src ./src
RUN mvn -q -DskipTests package

# ========= STAGE 2: Runtime =========
FROM eclipse-temurin:21-jre

# (Opcional) Zona horaria si la necesitas
ENV TZ=America/Lima

# Ajustes JVM para contenedores (memoria, etc.)
ENV JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

# Crea usuario no root
RUN useradd -ms /bin/bash spring
USER spring

WORKDIR /app
# Copia el JAR generado por Maven (ajusta el nombre si usas classifier)
COPY --from=build /src/target/*.jar app.jar

# Expone el puerto de tu app
EXPOSE 8080

# (Opcional) Activa un perfil
# ENV SPRING_PROFILES_ACTIVE=prod

# (Opcional) HEALTHCHECK simple
# HEALTHCHECK --interval=30s --timeout=3s --start-period=30s \
#   CMD wget -qO- http://127.0.0.1:8080/actuator/health | grep UP || exit 1

ENTRYPOINT ["java","-jar","/app/app.jar"]