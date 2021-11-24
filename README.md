# OVERIDE - Microservicio de trayectos

<p align="center">
  <img src="https://user-images.githubusercontent.com/78517969/142133621-0b331bae-3b32-403b-9f1e-d8a7c497ccbb.png" alt="DB_Model" />
  
</p>

Microservicio de manejo de trayectos de la applicacion OVERIDE, contiene toda la logica para el manejo de trayectos mediante el uso de una API Rest.

## 💻 Requisitos

* Java 17
* PostgreSQL
* Docker

## 🛠️ Guia de configuracion

El proyecto se encuentra corriendo bajo un host de docker, es posible utilizar el proyecto de manera local utilizando Java o utilizando docker


### Configuracion del archivo application.properties

Se debe crear un archivo llamado **application.properties** en la ruta /src/main/resources/ dentro de la carpeta resources, este archivo es el encargado de configurar la base de datos de la aplicación.

```
# DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url=jdbc:h2:mem:rides
spring.datasource.username=admin
spring.datasource.password=admin

spring.h2.console.enabled=true
spring.datasource.driverClassName=org.h2.Driver

# Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

### Configuración tradicional

La guia de configuracion esta creada bajo comandos de Windows. Todos los comandos se deben realizar en la raiz del proyecto (carpeta del proyecto) a la altura del archivo mvnw.

#### 1️⃣ Iniciar el servidor del proyecto
```console
./mvnw spring-boot:run
```

### Configuracion via Docker

La guia de configuracion esta creada bajo comandos de Windows. Todos los comandos se deben realizar en la raiz del proyecto (carpeta del proyecto) a la altura del archivo mvnw.

#### 1️⃣ Inicio del servidor Docker
```console
docker-compose up -d
```

Para cerrar el servidor una vez inicializado se debe usar el comando:

#### ⏹️ Cerrar servidor Docker
```console
docker-compose down
```


## 💾 Base de Datos
El proyecto esta configurado con una base de datos H2 para el ambiente de desarrollo, esta tiene disponible una interfaz grafica en donde se puede manipular y ver la información de la misma. Se puede acceder a esta mediante la ruta:
```
http://localhost:8080/h2-console
```
Con los siguientes datos:

<ul>
    <li>JDBC URL = jdbc:h2:mem:rides</li>
    <li>User Name: admin</li>
    <li>Password: admin</li>
</ul>

## ⚙️ API

La aplicacion esta formada por dos subsistemas, el subsistema de ciudades y el subsistemma de trayectos

#### 🟢 Obtener ciudades
Devuelve una lista de todas las ciudades almacenadas en la base de datos

```
http://localhost:8080/cities/all
```

#### 🟢 Obtener ciudad por id o por codigo
Devuelve una ciudad que corresponda al id especificado o al codigo especificado

```
http://localhost:8080/cities/id/<int:id>
```
```
http://localhost:8080/cities/code/<str:code>
```

Ejemplo:
```
http://localhost:8080/cities/id/1
```
```
http://localhost:8080/cities/code/bog
```
```json
{
    "id": 1,
    "code": "BOG",
    "name": "Bogota"
}
```

#### 🟢 Crear ciudad
Crea una ciudad con la información suministrada

```
http://localhost:8080/cities/create
```

Ejemplo:
```
http://localhost:8080/cities/create
```
```json
{
    "code": "BOG",
    "name": "Bogota"
}
```

#### 🟢 Editar ciudad por id
Devuelve la ciudad editada por el id especificado, es posible especificar solo los campos que se quieren editar ya sea codigo y nombre, solo codigo o solo nombre.

Ejemplo:
```
http://localhost:8080/cities/id/1
```

Editar ambos campos
```json
{
    "code": "MED",
    "name": "Medellin"
}
```
Editar un campo en especifico
```json
{
    "code": "MED"
}
```
```json
{
    "name": "Medellin"
}
```

#### 🟢 Eliminar ciudad por id
Elimina la ciudad con el id especificado.

Ejemplo:
```
http://localhost:8080/cities/id/1
```

#### 🟢 Obtener trayectos
Devuelve una lista de todas los trayectos registrados en la base de datos.

```
http://localhost:8080/rides/all
```

#### 🟢 Obtener trayecto por id
Devuelve una trayecto que corresponda al id especificado

```
http://localhost:8080/rides/id/<int:id>
```

Ejemplo:
```
http://localhost:8080/rides/id/1
```
```json
{
    "id": 1,
    "from": {
        "id": 1,
        "code": "BOG",
        "name": "Bogota"
    },
    "to": {
        "id": 2,
        "code": "MED",
        "name": "Medellin"
    },
    "departure_date": "2021-11-20",
    "departure_time": "10:30",
    "passengers": 30,
    "price": 100000
}
```

#### 🟢 Crear trayecto
Crea un trayecto con la información suministrada

```
http://localhost:8080/rides/create
```

Ejemplo:
```
http://localhost:8080/rides/create
```
```json
{
    "from": {
        "code": "BOG"
    },
    "to": {
        "code": "MED"
    },
    "departure_date": "2021-11-20",
    "departure_time": "10:30",
    "passengers": 30,
    "price": 100000
}
```

#### 🟢 Editar trayecto por id
Devuelve el trayecto editado por el id especificado, es posible especificar solo los campos que se quieren editar ya sea ciudad de origen, ciudad de destino, fecha de salida, hora de salida, pasajeros o precio.

* Los campos de ciudad se pueden editar con el campo code, si no se especifica no edita el campo
* Si no se especifica un campo no se edita


Ejemplo:
```
http://localhost:8080/rides/id/1
```
```json
{
    "from": {
        "code": "BOG"
    },
    "to": {
        "code": "CAR"
    },
    "departure_date": "2021-11-21",
    "departure_time": "10:30",
    "passengers": 30,
    "price": 100000
}
```

#### 🟢 Eliminar trayecto por id
Elimina el trayecto con el id especificado.

Ejemplo:
```
http://localhost:8080/rides/id/1
```


## 📝 Notas

#### 0.1.0

* Inicio del proyecto
* Creada configuración inicial del proyecto
* Configurada base de datos H2 para desarrollo
* Creada configuración de Docker

#### 0.2.0

* Creados servicios REST para ciudades

#### 0.3.0

* Creados Servicios REST para trayectos
* Creadas validaciones para ciudades
* Creadas validaciónes para trayectos
* Creado manejador de errores para la aplicación
