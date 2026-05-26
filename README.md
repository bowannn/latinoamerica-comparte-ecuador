# Latinoamérica Comparte - Módulo Ecuador
## Documentación Técnica Oficial del Proyecto Integrador

**Repositorio GitHub:** [https://github.com/bowannn/latinoamerica-comparte-ecuador](https://github.com/bowannn/latinoamerica-comparte-ecuador) 

**Institución:** Universidad Santo Tomás - Tunja 

---

##  Información del Grupo e Integrantes 

* **Nicolás Castañeda Martínez**
* **Juan Sebastián Silva Vega**
* **Gustavo Alfredo Gutiérrez Quintero**

---

##  Descripción del Proyecto

**Latinoamérica Comparte - Módulo Ecuador** es una solución de backend empresarial desarrollada en Java utilizando el framework Spring Boot. Su propósito principal es centralizar, validar y gestionar los flujos de información y participación ciudadana orientados a la región de Ecuador.

El sistema resuelve de manera directa la necesidad de proveer un núcleo transaccional seguro y escalable enfocado en dos pilares operativos fundamentales:

1. **Gestión Informativa Dinámica:** Procesamiento, almacenamiento y exposición controlada de noticias y eventos de impacto sociopolítico y cultural en las regiones ecuatorianas.
2. **Validación de Testimonios Comunitarios:** Módulo de auditoría y moderación de comentarios, roles y experiencias enviados por los usuarios de la plataforma, garantizando la calidad y veracidad de los datos antes de su persistencia final.

La aplicación está dirigida tanto a los administradores de la plataforma (quienes gestionan y aprueban el contenido) como a la comunidad general consumidora de la información. Cuenta con un componente automatizado de inicialización de datos (`DataInitializer`) que asegura la consistencia y disponibilidad del entorno operativo real desde el primer arranque, precargando registros de usuarios administrativos, noticias iniciales y testimonios aprobados listos para su consumo inmediato.

---

##  Stack de Tecnologías Utilizadas

* **Backend Framework:** Spring Boot 4.0.6 (Spring Core, Spring Security)
* **Java Virtual Machine:** OpenJDK 21 (LTS)
* **ORM & Data Access:** Hibernate ORM 7.2.12.Final & Spring Data JPA
* **Database Driver:** PostgreSQL JDBC Driver
* **Connection Pool:** HikariCP
* **Embedded Web Server:** Apache Tomcat 11.0.21
* **Gestor de Dependencias:** Maven

---

##  Pasos para Correr el Proyecto Localmente

### 1. Requisitos Previos
* **Java Development Kit (JDK):** Versión 21 o superior instalado en el sistema operativo.
* **Gestor de Dependencias:** Apache Maven (Gestionado automáticamente mediante el comando wrapper `mvnw` incluido en el proyecto).
* **Base de Datos:** PostgreSQL 15 o superior.
* **IDE Recomendado:** IntelliJ IDEA (Community/Ultimate) o VS Code con Extension Pack para Java.

### 2. Configuración del Archivo de Propiedades (`application.properties`)
Para ejecutar el proyecto en su entorno de desarrollo, asegúrese de crear una base de datos local llamada `ecuador_comparte_db` y configurar las siguientes propiedades dentro del archivo `src/main/resources/application.properties` o inyectarlas como variables de entorno:

```properties
# Cadena de Conexión y Credenciales JDBC
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/ecuador_comparte_db?ssl=true&sslmode=require
SPRING_DATASOURCE_USERNAME=tu_usuario_postgres
SPRING_DATASOURCE_PASSWORD=tu_contraseña_postgres

# Configuración Crítica del Pool de Conexiones (Optimizado para evitar saturación)
SPRING_DATASOURCE_HIKARI_MAXIMUM_POOL_SIZE=3
SPRING_DATASOURCE_HIKARI_CONNECTION_TIMEOUT=30000

# Estrategia de Creación de Tablas con Hibernate ORM
PRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=true

```

>  **Análisis Técnico del Pool de Conexiones:** El parámetro `SPRING_DATASOURCE_HIKARI_MAXIMUM_POOL_SIZE` se limitó estrictamente a 3. Esto soluciona de raíz el error de desbordamiento de conexiones simultáneas que ocurre al desplegar en capas gratuitas compartidas de bases de datos, garantizando estabilidad transaccional resiliente sin agotar los hilos de ejecución permitidos por el servidor remoto.
>
>

3. Comandos para Instalar Dependencias, Compilar y Ejecutar

Abre una terminal o consola de comandos en la carpeta raíz del proyecto y ejecuta:

En entornos Windows (CMD / PowerShell):

```cmd
mvnw clean package
mvnw spring-boot:run
```

En entornos Linux / macOS:

```bash
chmod +x mvnw
./mvnw clean package
./mvnw spring-boot:run
```


**URL de Acceso Local:** Cuando los logs de la consola confirmen que Tomcat se ha iniciado correctamente en el puerto 8080, podrás acceder al índice base mediante: http://localhost:8080/.



---

 Despliegue en la Nube (Render Cloud)

El backend empresarial se encuentra empaquetado mediante contenedores Docker y desplegado de manera continua en la plataforma Cloud de Render.


**URL Pública del API Activa:** https://latinoamerica-comparte-ecuador.onrender.com



>  **Nota sobre el Estado de Hibernación (Cold Start):** Al operar sobre la capa gratuita de Render, la instancia del contenedor entra en modo de reposo automático tras 15 minutos de inactividad. La recepción de una nueva petición HTTP iniciará la reactivación del servidor embebido, lo cual toma entre 50 y 60 segundos mientras se levanta el contexto de Spring Boot y se reconecta el pool de datos. Se sugiere realizar pruebas de consumo usando clientes REST locales o el Postman Desktop Agent para evitar cortes por límites de tiempo (timeout).
>
>

---

Estructura de Carpetas del Proyecto (`src/`)

El código fuente del proyecto está organizado bajo los estándares de una arquitectura limpia y modular en capas, facilitando el desacoplamiento de responsabilidades:

```text
src/
└── main/
    ├── java/
    │   └── d/
    │       └── g/
    │           └── integrador/
    │               ├── controller/       <-- Controladores REST (HTTP Endpoints)
    │               ├── entity/           <-- Entidades y Modelos ORM (Persistencia)
    │               ├── repository/       <-- Interfaces de Spring Data JPA
    │               ├── service/          <-- Lógica y Reglas Empresariales 
    │               └── IntegradorApplication.java
    └── resources/
        ├── static/                       <-- Archivos estáticos
        ├── templates/                    <-- Plantillas de vista
        └── application.properties        <-- Configuración Centralizada del Sistema

```

---

 Arquitectura del Sistema

La aplicación implementa el patrón de arquitectura MVC (Model-View-Controller) para desacoplar las responsabilidades del backend, delegando el control de persistencia e inyección de datos a los servicios transaccionales de Spring Data JPA.

El flujo inicia en el controlador expuesto, pasa por las validaciones lógicas del servicio y se ejecuta en la capa de datos.

---

 Rutas y Endpoints Disponibles del API

Endpoints públicos que retornan la información en formato estructurado JSON:

| Funcionalidad | Ruta (URI) | Método | Descripción | Acceso |
| --- | --- | --- | --- | --- |
| Home fallback | *No asignada* | GET | Ruta raíz por defecto (Muestra Whitelabel Error al no tener vista principal asignada). | Público |
| Lista de Noticias | `/api/news` | GET | Consulta y expone todas las noticias vigentes precargadas en el sistema. | Público |
| Testimonios | `/api/testimonials` | GET | Filtra y retorna los testimonios comunitarios aprobados (approved true). | Público |

(Nota: Datos basados en la tabla técnica de endpoints del proyecto ).

---

Preguntas Orientadoras de Evaluación

1. ¿Qué versión de Java estás utilizando?

Estamos utilizando Java 21 (LTS). Seleccionamos esta versión porque es la versión de soporte a largo plazo más estable en la actualidad, ofreciendo mejoras sustanciales en rendimiento de gestión de memoria, optimizaciones internas para microservicios y soporte nativo completo para las características avanzadas de Spring Boot de última generación.

2. ¿Qué dependencias de automatización estás utilizando y en qué versión se encuentran?

Al ser un proyecto de ingeniería gestionado y automatizado mediante Maven (a través del archivo de configuración centralizado `pom.xml`), las dependencias clave provienen del ecosistema Spring Boot en su versión estable 4.0.6:

*
**`spring-boot-starter-data-jpa` (v4.0.6):** Para la abstracción del acceso a datos, manejo de repositorios genéricos y comunicación ORM.


*
**`spring-boot-starter-web` (v4.0.6):** Para la creación e infraestructura de los servicios web REST y la integración del servidor web embebido Tomcat.


*
**Driver JDBC de PostgreSQL:** Encargado de gestionar la comunicación de datos y red con el motor relacional alojado en la infraestructura Cloud.



---

Análisis Personal: Retos y Aprendizajes Más Valiosos

¿Cuáles fueron los retos más grandes encontrados?

El reto técnico más complejo de este proyecto fue lograr la estabilidad del backend una vez desplegado en producción dentro de la nube de Render. Inicialmente, el sistema colapsaba al recibir peticiones y entraba en un bucle interminable de carga.

Tras una lectura detallada de los logs de ejecución, diagnosticamos que el pool de conexiones por defecto (HikariPool) intentaba abrir más hilos de los permitidos por las restricciones estrictas de concurrencia de la base de datos en su capa compartida. El gran reto consistió en investigar y solucionar esto de raíz configurando de manera precisa las propiedades de entorno, reduciendo el pool máximo a 3 y estableciendo un tiempo de espera de respuesta controlado.

¿Cuáles fueron los aprendizajes técnicos más valiosos?

El aprendizaje más valioso fue experimentar y apropiarse del ciclo de vida completo de un desarrollo de software de nivel empresarial: desde el diseño conceptual del modelo de entidades en la base de datos relacional, pasando por la lógica modular del lenguaje Java, hasta la madurez de empaquetar la aplicación, subirla a un entorno real en la nube y realizar un monitoreo analítico mediante logs de consola para diagnosticar y resolver fallas críticas de infraestructura.
