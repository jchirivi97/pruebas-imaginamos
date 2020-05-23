## Prueba Imaginamos


## Elaborado por:

Jimmy Armando Chirivi Nivia

## Descripcion

Se requiere construir un sistema de órdenes de servicio para una empresa que ofrece servicios de mantenimiento e
instalación de soportes para televisores. Los clientes pueden hacer una solicitud de servicio generando un ticket a través
del sistema, el cual una vez valide la identificación del cliente debe generar un estado inicial de la solicitud, generar un
token y asignar a un técnico de forma aleatoria para que atienda la solicitud, crear un link para seguimiento del servicio y
crear un link para calificar el servicio.

Por último, los técnicos pueden ver las órdenes asignadas que debe atender en transcurso del día y es necesario contar
con un endpoint que retorne los servicios del técnico en formato JSON con el listado.


## Tecnolgias de Desarrollo

- Java 8
- JavaScript 
- PostgreSQL
- Maven
- Spring Boot


## Despliegue

Heroku: 

## Usuarios de Pruebas

- Cliente :

tipo documento : cc
cedula: 123456789
contraseña: 1234

- Tecnico: 

- tipo documento : cc
cedula: 66666
contraseña: 1234

-tipo documento : cc
cedula: 888888
contraseña: 1234

## Diagrama de Datos

![Captura](https://user-images.githubusercontent.com/48265107/82731299-64b37c80-9ccb-11ea-864c-3935ebcc7174.JPG)

