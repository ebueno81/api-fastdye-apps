# ========= RUNTIME =========
FROM eclipse-temurin:21-jre

# Zona horaria (opcional)
ENV TZ=America/Lima

# Ajustes JVM para contenedores (opcional)
ENV JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

# Crear usuario no root
RUN useradd -ms /bin/bash spring
USER spring

WORKDIR /app

# 👉 Copia el JAR construido por Jenkins
COPY target/*.jar app.jar

# Exponer el puerto que usas en Spring Boot
EXPOSE 8080

# EntryPoint
ENTRYPOINT ["java","-jar","/app/app.jar"]
