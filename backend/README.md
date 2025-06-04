# Planificación del Proyecto

## Funcionalidades Core (MVP)

- **Autenticación de usuarios** (registro/login)
- **CRUD de proyectos**
- **CRUD de tareas** dentro de proyectos
- **Asignación de tareas** a usuarios
- **Estados básicos de tareas**: pendiente, en progreso, completada

---

## Stack Tecnológico

### Backend

- Java 17+ con Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- H2/PostgreSQL
- Arquitectura Hexagonal

### Frontend

- React 19 con TypeScript
- Material-UI para UI
- React Router para navegación
- Context API para estado global

---

## Plan de Desarrollo (3 días)

### Día 1: Backend - Infraestructura y Autenticación

**Paso 1: Configuración inicial del proyecto**

```bash
curl https://start.spring.io/starter.zip \
  -d dependencies=web,security,data-jpa,h2,validation \
  -d type=maven-project \
  -d javaVersion=17 \
  -d artifactId=project-management-api \
  -o project-management-api.zip
```

**Paso 2: Estructura de Arquitectura Hexagonal**

```
src/main/java/com/example/projectmanagement/
├── domain/
│   ├── model/        # Entidades de dominio
│   ├── port/         # Interfaces (puertos)
│   └── service/      # Lógica de negocio
├── infrastructure/
│   ├── adapter/
│   │   ├── in/       # Controladores REST
│   │   └── out/      # Repositorios JPA
│   ├── config/       # Configuraciones
│   └── security/     # JWT, Security Config
└── application/      # Casos de uso
```

**Paso 3: Implementar autenticación JWT**

- Configurar Spring Security
- Crear filtros JWT
- Endpoints de registro y login

---

### Día 2: Backend - Dominio y Casos de Uso

**Paso 4: Modelado del dominio**

- Entidades: `User`, `Project`, `Task`
- Puertos (interfaces): `UserRepository`, `ProjectRepository`, `TaskRepository`
- Servicios de dominio

**Paso 5: Implementar casos de uso**

- Gestión de proyectos (CRUD)
- Gestión de tareas (CRUD)
- Asignación de tareas

**Paso 6: Adaptadores de persistencia**

- Implementar repositorios JPA
- Configurar base de datos

---

### Día 3: Frontend y Integración

**Paso 7: Setup del Frontend**

```bash
npx create-react-app project-management-ui --template typescript
cd project-management-ui
npm install axios react-router-dom @types/react-router-dom
```

**Paso 8: Estructura del Frontend**

```
src/
├── components/   # Componentes reutilizables
├── pages/        # Páginas principales
├── services/     # Llamadas a API
├── context/      # Context API
├── types/        # Tipos TypeScript
└── utils/        # Utilidades
```

**Paso 9: Implementar funcionalidades**

- Sistema de autenticación
- Dashboard de proyectos
- Gestión de tareas
- Navegación entre vistas

---

## Cronograma Detallado

### Viernes Noche (3-4 horas)

- Setup inicial de ambos proyectos
- Configuración de arquitectura hexagonal
- Implementación básica de JWT

### Sábado (8 horas)

**Mañana (4 horas):**

- Completar autenticación backend
- Modelado de dominio
- Casos de uso básicos

**Tarde (4 horas):**

- Repositorios y persistencia
- Testing de endpoints
- Setup del frontend

### Domingo (8 horas)

**Mañana (4 horas):**

- Autenticación frontend
- Componentes principales
- Integración con backend

**Tarde (4 horas):**

- Funcionalidades CRUD
- Styling básico
- Testing y deployment

---

## Consejos para el Éxito

- Mantén el MVP simple: solo las funcionalidades esenciales
- Usa H2 inicialmente para desarrollo rápido
- Testing básico: unit tests para servicios críticos
- Docker para deployment fácil
- Documentación: README con instrucciones de setup

---

## Tecnologías de Apoyo Recomendadas

- **Postman/Insomnia:** Para testing de APIs
- **Docker:** Para containerización
- **Git:** Control de versiones desde el inicio
- **H2 Console:** Para visualizar datos durante desarrollo

# Notas

## Estructura de Carpetas y Archivos detallada

```
src/main/java/com/example/projectmanagement/
├── ProjectManagementApplication.java
├── domain/
│   ├── model/
│   │   ├── User.java
│   │   ├── Project.java
│   │   └── Task.java
│   ├── port/
│   │   ├── in/
│   │   │   ├── AuthUseCase.java
│   │   │   ├── ProjectUseCase.java
│   │   │   └── TaskUseCase.java
│   │   └── out/
│   │       ├── UserRepositoryPort.java
│   │       ├── ProjectRepositoryPort.java
│   │       └── TaskRepositoryPort.java
│   └── service/
│       ├── AuthService.java
│       ├── ProjectService.java
│       └── TaskService.java
├── infrastructure/
│   ├── adapter/
│   │   ├── in/
│   │   │   ├── rest/
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── ProjectController.java
│   │   │   │   └── TaskController.java
│   │   │   └── dto/
│   │   │       ├── request/
│   │   │       │   ├── LoginRequest.java
│   │   │       │   ├── RegisterRequest.java
│   │   │       │   ├── ProjectRequest.java
│   │   │       │   └── TaskRequest.java
│   │   │       └── response/
│   │   │           ├── AuthResponse.java
│   │   │           ├── ProjectResponse.java
│   │   │           └── TaskResponse.java
│   │   └── out/
│   │       ├── persistence/
│   │       │   ├── entity/
│   │       │   │   ├── UserEntity.java
│   │       │   │   ├── ProjectEntity.java
│   │       │   │   └── TaskEntity.java
│   │       │   ├── repository/
│   │       │   │   ├── UserJpaRepository.java
│   │       │   │   ├── ProjectJpaRepository.java
│   │       │   │   └── TaskJpaRepository.java
│   │       │   └── adapter/
│   │       │       ├── UserRepositoryAdapter.java
│   │       │       ├── ProjectRepositoryAdapter.java
│   │       │       └── TaskRepositoryAdapter.java
│   │       └── mapper/
│   │           ├── UserMapper.java
│   │           ├── ProjectMapper.java
│   │           └── TaskMapper.java
│   ├── config/
│   │   ├── BeanConfig.java
│   │   └── CorsConfig.java
│   └── security/
│       ├── SecurityConfig.java
│       ├── JwtAuthenticationFilter.java
│       ├── JwtTokenProvider.java
│       └── CustomUserDetailsService.java
└── application/
    ├── usecase/
    │   ├── AuthUseCaseImpl.java
    │   ├── ProjectUseCaseImpl.java
    │   └── TaskUseCaseImpl.java
    └── exception/
        ├── GlobalExceptionHandler.java
        ├── ResourceNotFoundException.java
        └── ValidationException.java

```
