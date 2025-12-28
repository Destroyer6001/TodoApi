# 📝 API Gestor de Tareas - Spring Boot

API RESTful para gestión de tareas y estados desarrollada con Spring Boot 3.5.9+, PostgreSQL y Lombok.

## 🚀 Características

- Gestión completa de tareas (CRUD)
- Administración de estados de tareas (pendiente, en progreso, completada)
- Filtrado y búsqueda de tareas
- Asignación de fechas límite y prioridades
- Validación de datos y manejo de errores estandarizado

## 🛠 Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3.5.9.x**
- **PostgreSQL 14+**
- **Lombok** - Reducción de código boilerplate
- **Maven** - Gestión de dependencias
- **Spring Data JPA** - Persistencia de datos
- **Spring Web** - API REST

## 📋 Prerrequisitos

### 1. Java Development Kit (JDK) 17 o superior
```bash
java -version
```

### 2. Maven 3.8+
```bash
mvn -v
```

### 3. Git
```bash
git --version
```

## ⚙️ Configuración del Entorno

### 1. Clonar el Repositorio
```bash
git clone https://github.com/Destroyer6001/TodoApi.git
cd todo-list-api
```

### 2. Configurar la Aplicación

Editar `src/main/resources/application.properties` con las configuraciones necesarias.

### 3. Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080/api`

# 📋 Endpoints API ToDo List

## 📊 Endpoints Disponibles

### **Categorías**
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **GET** | `https://todoapi-op0h.onrender.com/api/categories` | Obtiene todas las categorías |
| **GET** | `https://todoapi-op0h.onrender.com/api/categories/{id}` | Obtiene categoría por ID |
| **POST** | `https://todoapi-op0h.onrender.com/api/categories` | Crea una nueva categoría |
| **PUT** | `https://todoapi-op0h.onrender.com/api/categories/{id}` | Actualiza categoría existente |
| **DELETE** | `https://todoapi-op0h.onrender.com/api/categories/{id}` | Elimina una categoría |

### **Tareas**
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **GET** | `https://todoapi-op0h.onrender.com/api/tasks` | Obtiene todas las tareas |
| **GET** | `https://todoapi-op0h.onrender.com/api/tasks/{id}` | Obtiene tarea por ID |
| **POST** | `https://todoapi-op0h.onrender.com/api/tasks` | Crea una nueva tarea |
| **PUT** | `https://todoapi-op0h.onrender.com/api/tasks/{id}` | Actualiza tarea existente |
| **PATCH** | `https://todoapi-op0h.onrender.com/api/tasks/{id}` | Cambia estado de la tarea |
| **DELETE** | `https://todoapi-op0h.onrender.com/api/tasks/{id}` | Elimina una tarea |

## 🔑 Parámetros

### Path Parameters
- `{id}`: ID del recurso (categoría o tarea)

## 📄 Formato de Respuesta

Todas las respuestas siguen el formato `ApiResponseDTO<T>`:
```json
{
  "success": true,
  "message": "Operación exitosa",
  "data": { ... },
  "timestamp": "2024-01-15T10:30:00Z"
}
```
## 🔗 URL Base
```
https://todoapi-op0h.onrender.com
```

## 🔗 Enlace para Clonar

```bash
git clone https://github.com/Destroyer6001/TodoApi.git
```
