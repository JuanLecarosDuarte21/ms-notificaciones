# Microservicio de Notificaciones - TiendaPC

## Descripción

Microservicio desarrollado con Java y Spring Boot para gestionar las notificaciones de una tienda en línea de componentes de PC.

Permite registrar, consultar, filtrar, actualizar el estado y eliminar notificaciones relacionadas con pedidos, pagos y envíos.

Las notificaciones se almacenan en MySQL. El envío de correos electrónicos reales no está implementado; se utiliza una simulación para fines académicos.

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.0.8
- Spring Web
- Spring Data JPA
- MySQL
- Maven
- Jakarta Validation
- Git y GitHub

## Configuración

**Puerto:** 8088

**Base de datos:** `tienda_notificaciones_db`

**URL base:** `http://localhost:8088/api/notificaciones`

La conexión a MySQL utiliza las variables de entorno `DB_USERNAME` y `DB_PASSWORD`. El usuario predeterminado es `root`.

## Estructura del proyecto

- `controller`: endpoints REST.
- `model`: entidad Notificacion.
- `repository`: acceso a la base de datos.
- `service`: lógica de negocio.
- `config`: carpeta reservada para configuraciones adicionales.

## Endpoints REST

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/notificaciones` | Crear notificación |
| GET | `/api/notificaciones` | Listar todas |
| GET | `/api/notificaciones/{id}` | Buscar por ID |
| GET | `/api/notificaciones/destinatario/{destinatario}` | Buscar por destinatario |
| GET | `/api/notificaciones/tipo/{tipo}` | Buscar por tipo |
| GET | `/api/notificaciones/estado/{estado}` | Buscar por estado |
| GET | `/api/notificaciones/destinatario/{destinatario}/estado/{estado}` | Filtrar por destinatario y estado |
| PUT | `/api/notificaciones/{id}/estado` | Actualizar estado |
| DELETE | `/api/notificaciones/{id}` | Eliminar notificación |

## Ejemplo de creación

**POST** `/api/notificaciones`

```json
{
  "destinatario": "cliente@ejemplo.com",
  "mensaje": "Tu pedido ha sido confirmado",
  "tipo": "PEDIDO"
}
```

La notificación se registra inicialmente con estado `PENDIENTE`.

## Ejemplo de actualización de estado

**PUT** `/api/notificaciones/1/estado`

```json
{
  "estado": "ENVIADA"
}
```

Este cambio actualiza el registro en la base de datos, sin enviar un mensaje real.

## Ejecución del proyecto

Primero, crear la base de datos:

```sql
CREATE DATABASE IF NOT EXISTS tienda_notificaciones_db;
```

Configurar la contraseña de MySQL en PowerShell:

```powershell
$env:DB_PASSWORD = "TU_CONTRASEÑA_MYSQL"
```

Ejecutar la aplicación:

```powershell
.\mvnw.cmd spring-boot:run
```

Compilar el proyecto:

```powershell
.\mvnw.cmd clean package
```

## Control de versiones

El proyecto utiliza tres ramas:

- `main`: versión principal.
- `develop`: rama de desarrollo.
- `feature/documentacion`: rama para la documentación.

## Proyecto académico

Este microservicio forma parte de TiendaPC, un sistema académico de comercio electrónico compuesto por ocho microservicios.