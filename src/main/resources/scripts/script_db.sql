-- Base de Datos SpringMVC_2025
CREATE DATABASE SpringMVC_2025;
USE SpringMVC_2025; 

-- Creamos la tabla Productos
CREATE TABLE Productos(
	id          INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre      VARCHAR(90) NOT NULL,
    descripcion VARCHAR(90) NOT NULL,
    precio      DOUBLE      NOT NULL
);

SELECT * FROM Productos;