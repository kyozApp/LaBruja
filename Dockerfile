#* Version del proyecto (java21)
FROM eclipse-temurin:21 as base

#* Nombre de la empresa o desarrollador
LABEL author=kyoz.com

#* Definir las asignaciones de puertos para los contenedores de la plataforma que se encuentran en su etapa de ejecución
EXPOSE 8080

#! Ejecuta por terminal
#? mvn clean package

#* Copia el maven que ejecutastes
ADD target/spring-boot-docker.jar app.jar

#* Definir el comando que se ejecuta automáticamente al iniciar un contenedor
ENTRYPOINT [ "java", "-jar", "/app.jar" ]

#! Ejecuta por terminal
#? docker build -t springservice .

#! Ejecuta por terminal
#? docker run -it --rm -p 8081:8080 --name myspringservice springservice