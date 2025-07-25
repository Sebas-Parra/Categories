# 📦 Categories API

API REST desarrollada en Java con Spring Boot 3 (JDK 17) que permite gestionar un catálogo de categorías. La aplicación se ejecuta completamente en contenedores Docker (aplicación + base de datos MySQL).

---

## 🚀 Características principales

- CRUD completo para entidades `Category`.
- Persistencia de datos en MySQL.
- Implementación moderna con Spring Boot 3 y JDK 17.
- Despliegue Docker multi-stage para imágenes ligeras.
- Imagen publicada en Docker Hub.

---

## 🧱 Modelo de datos: `Category`

| Campo       | Tipo            | Reglas                                 |
|-------------|------------------|----------------------------------------|
| `id`        | `Long`          | Auto-generado, clave primaria          |
| `name`      | `String`        | Obligatorio, único, 3 a 50 caracteres  |
| `description` | `String`      | Opcional, hasta 255 caracteres         |
| `createdAt` | `LocalDateTime` | Se asigna automáticamente al crear     |

---

## 📌 Endpoints REST

| Método  | Ruta                         | Descripción                        |
|---------|------------------------------|------------------------------------|
| `POST`  | `/api/categories`            | Crear una categoría                |
| `GET`   | `/api/categories`            | Listar todas las categorías        |
| `GET`   | `/api/categories/{id}`       | Buscar por ID                      |
| `PATCH` | `/api/categories/{id}`       | Actualizar parcialmente una categoría |
| `DELETE`| `/api/categories/{id}`       | Eliminar una categoría             |

---

## 🐳 Docker

### 📄 Dockerfile

El `Dockerfile` se encuentra en la raíz del proyecto y utiliza una **construcción multi-stage** para minimizar el tamaño final de la imagen:

```dockerfile
FROM openjdk:17-jdk-alpine as builder
WORKDIR /app

COPY ./pom.xml ./pom.xml
COPY .mvn .mvn
COPY mvnw mvnw

RUN ./mvnw dependency:go-offline

COPY ./src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/target/categories-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```
## Construcción de la imagen y al Docker hub
```bash
docker build -t sebraxter/img-categories:v1 .
docker login
docker push sebraxter/img-categories:v1
```
## Base de datos
```bash
docker run --name mysql-categories \
  -e MYSQL_ROOT_PASSWORD=admin123 \
  -e MYSQL_DATABASE=test \
  -p 3306:3306 \
  -d mysql:8
```
