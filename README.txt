Para ejecutar el siguiente programa debe tener en cuenta lo siguiente:

1. Tener arriba la base de datos Oracle, se muestra una versión de una base de datos en Docker 
2. Ejecutar sentencias del archivo "Script_SQL.sql" en orden para crear tablas, trigger, secuencias, índices, paquetes e insertar datos de prueba en la base de datos. Verificar Diagrama Entidad Relación Construido.
3. Compilar proyecto Spring Boot adjunto
4. Verificar endpoint servicios construidos mediante collection de Postman adjunta "Prueba_Backend.postman_collection" o mediante servicio Swagger "http://localhost:8081/swagger-ui/index.html"
5. Verificar test unitarios construidos. 

Se proporciona a continuación comandos utilizados en el desarrollo y credenciales de prueba de los servicios construidos. 

#######################################################
	Ejecutar Docker de base de datos Oracle
#######################################################
docker run -d -p 49161:1521 -p 8080:8080 --name oracle-xe oracleinanutshell/oracle-xe-11g

#######################################################
		Ejecutar Swagger
#######################################################
http://localhost:8081/swagger-ui/index.html

#######################################################
	Autenticación usuario Admin
#######################################################
{
    "email": "admin@comercio.com",
    "contrasena": "admin123"
}

#######################################################
	Autenticación usuario Auxiliar
#######################################################
{
    "email": "auxiliar@comercio.com",
    "contrasena": "aux123"
}


#######################################################
	Comando para construir DockerFile
#######################################################
docker build -t comercio-api .
docker run -d -p 8081:8081 --name comercio-app comercio-api


NOTA: Al momento de dockerizar el proyecto, se presentaron incompatibilidades con las dependencias de Swagger (springdoc-openapi) y la versión de Spring Boot Parent.

Esto se debe a diferencias entre las versiones requeridas por las dependencias y el uso de Java 11 como base del proyecto. Por cuestiones de tiempo, no fue posible ajustar completamente estas versiones para asegurar una ejecución limpia en el entorno Docker. El proyecto funciona correctamente en un entorno local con Java 11 y puede ser ajustado fácilmente actualizando las dependencias y la imagen base si se migra a Java 17+.