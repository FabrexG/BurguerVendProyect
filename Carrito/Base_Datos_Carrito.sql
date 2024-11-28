-- Crear la base de datos (si no existe)
CREATE DATABASE Burguer;

-- Usar la base de datos
CONNECT TO Burguer;

-- Crear secuencias para las tablas
CREATE SEQUENCE secuencia_ingrediente START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE secuencia_extra START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE secuencia_hamburguesa START WITH 1 INCREMENT BY 1;

-- Tabla para almacenar los ingredientes
CREATE TABLE Ingrediente (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR2(255) NOT NULL,
    costo NUMBER(10, 2) NOT NULL,
    cantidad NUMBER NOT NULL, -- Cantidad disponible del ingrediente
    unidad VARCHAR2(50) NOT NULL -- Unidad de medida (gramos, unidades, etc.)
);

-- Trigger para la tabla Ingrediente
CREATE OR REPLACE TRIGGER trigger_ingrediente_id
BEFORE INSERT ON Ingrediente
FOR EACH ROW
BEGIN
    SELECT secuencia_ingrediente.NEXTVAL INTO :new.id FROM dual;
END;
/

-- Tabla para almacenar los extras
CREATE TABLE Extra (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR2(255) NOT NULL,
    costo NUMBER(10, 2) NOT NULL,
    cantidad NUMBER NOT NULL -- Cantidad disponible del extra
);

-- Trigger para la tabla Extra
CREATE OR REPLACE TRIGGER trigger_extra_id
BEFORE INSERT ON Extra
FOR EACH ROW
BEGIN
    SELECT secuencia_extra.NEXTVAL INTO :new.id FROM dual;
END;
/

-- Tabla para almacenar las hamburguesas
CREATE TABLE Hamburguesa (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR2(255) NOT NULL,
    costoBase NUMBER(10, 2) NOT NULL,
    rutaImagen VARCHAR2(255)
);

-- Trigger para la tabla Hamburguesa
CREATE OR REPLACE TRIGGER trigger_hamburguesa_id
BEFORE INSERT ON Hamburguesa
FOR EACH ROW
BEGIN
    SELECT secuencia_hamburguesa.NEXTVAL INTO :new.id FROM dual;
END;
/

-- Tabla intermedia para relacionar las hamburguesas con sus ingredientes
CREATE TABLE HamburguesaIngrediente (
    hamburguesa_id NUMBER,
    ingrediente_id NUMBER,
    cantidad NUMBER NOT NULL, -- Cantidad del ingrediente en la hamburguesa
    PRIMARY KEY (hamburguesa_id, ingrediente_id),
    FOREIGN KEY (hamburguesa_id) REFERENCES Hamburguesa(id),
    FOREIGN KEY (ingrediente_id) REFERENCES Ingrediente(id)
);

-- Tabla intermedia para relacionar las hamburguesas con sus extras
CREATE TABLE HamburguesaExtra (
    hamburguesa_id NUMBER,
    extra_id NUMBER,
    cantidad NUMBER NOT NULL, -- Cantidad del extra en la hamburguesa
    PRIMARY KEY (hamburguesa_id, extra_id),
    FOREIGN KEY (hamburguesa_id) REFERENCES Hamburguesa(id),
    FOREIGN KEY (extra_id) REFERENCES Extra(id)
);


-- Tabla para almacenar las imágenes de las hamburguesas
CREATE TABLE HamburguesaImagen (
    id NUMBER PRIMARY KEY, -- Identificador único para la imagen
    nombre VARCHAR2(255) NOT NULL, -- Nombre de la ahmburguesa
    rutaImagen VARCHAR2(500) NOT NULL -- Ruta completa o relativa de la imagen
);

-- Ingredientes
INSERT INTO Ingrediente (nombre, costo, cantidad, unidad) VALUES
('Queso', 1.00, 200, 'unidad'), -- 200 unidades de queso
('Carne de res', 2.50, 100, 'unidad'), -- 100 unidades de carne de res
('Pollo', 3.00, 50, 'unidad'), -- 50 unidades de pollo
('Guacamole', 1.50, 50, 'unidad'), -- 50 unidades de guacamole
('Lechuga', 0.50, 50, 'unidad'), -- 50 unidades de lechuga
('Tomate', 0.50, 50, 'unidad'); -- 50 unidades de tomate

-- Extras
INSERT INTO Extra (nombre, costo, cantidad) VALUES
('Papas fritas', 1.50, 100), -- 100 unidades de papas fritas
('Refresco', 1.00, 100), -- 100 unidades de refresco
('Helado', 2.00, 50), -- 50 unidades de helado
('Bacon', 1.50, 50); -- 50 unidades de bacon

--Imagen--
INSERT INTO HamburguesaImagen (id,nombre, rutaImagen) VALUES (1,'Hamburguesa Vegana', '/img/Hamburguesa4.png');
INSERT INTO HamburguesaImagen (id,nombre, rutaImagen) VALUES (2,'Hamburguesa Pollo', '/img/Hamburguesa3.png');
INSERT INTO HamburguesaImagen (id,nombre, rutaImagen) VALUES (3,'Hamburguesa Res', '/img/Hamburguesa2.png');
INSERT INTO HamburguesaImagen (id,nombre, rutaImagen) VALUES (4,'Hamburguesa a la Plancha', '/img/Hamburguesa1.png');



