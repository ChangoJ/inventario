DROP TABLE IF EXISTS detalle_ventas;
DROP TABLE IF EXISTS cabecera_ventas;
DROP TABLE IF EXISTS detalle_pedidos;
DROP TABLE IF EXISTS cabecera_pedidos;
DROP TABLE IF EXISTS historial_stock;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS proveedores;
DROP TABLE IF EXISTS categorias_unidad_medida;
DROP TABLE IF EXISTS unidades_medida;
DROP TABLE IF EXISTS estados_pedido;
DROP TABLE IF EXISTS tipo_documentos;
DROP TABLE IF EXISTS categorias;


create table categorias(
	codigo_cat serial not null,
	nombre varchar(100) not null,
	categoria_padre int ,
	constraint categorios_pk primary key(codigo_cat),
	constraint categoria_fk foreign key(categoria_padre)
	references categorias(codigo_cat)
);


INSERT INTO categorias(nombre, categoria_padre)
VALUES
('Materia prima',null),
('Proteina', 1),
('Salsas',1),
('Punto de venta',null),
('Bebidas', 4),
('Con Alcohol', 5),
('Sin Alcohol',5);

CREATE TABLE tipo_documentos (
    codigo char(1) NOT NULL,
    descripcion varchar(100) NOT NULL,
    PRIMARY KEY (codigo)
);

INSERT INTO tipo_documentos (codigo, descripcion) VALUES
('C', 'Cédula'),
('R', 'RUC');

CREATE TABLE categorias_unidad_medida (
    codigo_cudm serial NOT NULL,
    nombre varchar(100) NOT NULL,
    PRIMARY KEY (codigo_cudm)
);

INSERT INTO categorias_unidad_medida (nombre) VALUES
('Unidades'),
('Volumen'),
('Peso');

CREATE TABLE unidades_medida (
    codigo_udm varchar(100) NOT NULL,
    descripcion varchar(100) NOT NULL,
    PRIMARY KEY (codigo_udm)
);

INSERT INTO unidades_medida (codigo_udm, descripcion) VALUES
('ml', 'mililitros'),
('l', 'litros'),
('u', 'unidad'),
('d', 'docena'),
('g', 'gramos'),
('kg', 'kilogramos'),
('lb', 'libras');


CREATE TABLE proveedores (
    identificador varchar(100) NOT NULL,
    tipo_documento char(1) NOT NULL,
    nombre varchar(100) NOT NULL,
    telefono char(10) UNIQUE NOT NULL,
    correo varchar(100) UNIQUE NOT NULL,
    direccion varchar(100) NOT NULL,
    PRIMARY KEY (identificador),
    FOREIGN KEY (tipo_documento) REFERENCES tipo_documentos(codigo)
);

INSERT INTO proveedores (identificador, tipo_documento, nombre, telefono, correo, direccion) VALUES
('1792285747', 'C', 'SANTIAGO MOS', '0992920306', 'zantycb89@example.com', 'Cumbayork'),
('1792285747001', 'R', 'SNACKS SA', '0992920398', 'snacks@example.com', 'La Tola');


CREATE TABLE productos (
    codigo_producto serial NOT NULL,
    nombre varchar(100) NOT NULL,
    UDM varchar(100) NOT NULL,
    precio_venta money NOT NULL,
    tiene_iva boolean NOT NULL,
    coste money NOT NULL,
    categoria int NOT NULL,
    stock int NOT NULL,
    PRIMARY KEY (codigo_producto),
    FOREIGN KEY (UDM) REFERENCES unidades_medida(codigo_udm),
    FOREIGN KEY (categoria) REFERENCES categorias(codigo_cat)
);

INSERT INTO productos (nombre, UDM, precio_venta, tiene_iva, coste, categoria, stock) VALUES
('Coca cola pequeña', 'u', 0.5804, true, 0.3729, 7, 105),
('Salsa de tomate', 'kg', 0.95, true, 0.8736, 3, 0),
('Mostaza', 'kg', 0.95, true, 0.89, 3, 0),
('Fuze Tea', 'u', 0.8, true, 0.7, 7, 50);


CREATE TABLE cabecera_pedidos (
    numero serial NOT NULL,
    proveedor varchar(100) NOT NULL,
    fecha date NOT NULL,
    estado char(1) NOT NULL,
    PRIMARY KEY (numero),
    FOREIGN KEY (proveedor) REFERENCES proveedores(identificador),
    FOREIGN KEY (estado) REFERENCES tipo_documentos(codigo)
);

INSERT INTO cabecera_pedidos (numero, proveedor, fecha, estado) VALUES
(1, '1792285747', '2023-11-20', 'R'),
(2, '1792285747', '2023-11-20', 'R');

CREATE TABLE estados_pedido (
    codigo_estado_pedido char(1) NOT NULL,
    descripcion varchar(100) NOT NULL,
    PRIMARY KEY (codigo_estado_pedido)
);

INSERT INTO estados_pedido (codigo_estado_pedido, descripcion) VALUES
('S', 'Solicitado'),
('R', 'Recibido');

CREATE TABLE detalle_pedidos (
    codigo_detalle_pedido serial NOT NULL,
    cabecera_pedido int NOT NULL,
    producto int NOT NULL,
    cantidad int NOT NULL,
    subtotal money NOT NULL,
    cantidad_recibida int NOT NULL,
    PRIMARY KEY (codigo_detalle_pedido),
    FOREIGN KEY (cabecera_pedido) REFERENCES cabecera_pedidos(numero),
    FOREIGN KEY (producto) REFERENCES productos(codigo_producto)
);

INSERT INTO detalle_pedidos (cabecera_pedido, producto, cantidad, subtotal, cantidad_recibida) VALUES
(1, 1, 100, 37.29, 100),
(1, 4, 50, 11.8, 50),
(2, 1, 10, 3.73, 10);

CREATE TABLE cabecera_ventas (
    codigo_cabecera_ventas serial NOT NULL,
    fecha TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    total_sin_iva money NOT NULL,
    iva money NOT NULL,
    total money NOT NULL,
    PRIMARY KEY (codigo_cabecera_ventas)
);

INSERT INTO cabecera_ventas (fecha, total_sin_iva, iva, total) VALUES
('2023-11-20 20:00:00', 3.26, 0.39, 3.65);

CREATE TABLE detalle_ventas (
    codigo_detalle_venta serial NOT NULL,
    cabecera_ventas int NOT NULL,
    producto int NOT NULL,
    cantidad int NOT NULL,
    precio_venta money NOT NULL,
    subtotal money NOT NULL,
    subtotal_con_iva money NOT NULL,
    PRIMARY KEY (codigo_detalle_venta),
    FOREIGN KEY (cabecera_ventas) REFERENCES cabecera_ventas(codigo_cabecera_ventas),
    FOREIGN KEY (producto) REFERENCES productos(codigo_producto)
);

INSERT INTO detalle_ventas (cabecera_ventas, producto, cantidad, precio_venta, subtotal, subtotal_con_iva) VALUES
(1, 1, 5, 0.58, 2.9, 3.25),
(1, 4, 1, 0.36, 0.36, 0.4);

CREATE TABLE historial_stock (
    codigo_historial_stock serial NOT NULL,
    fecha TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    referencia varchar(100) NOT NULL,
    producto int NOT NULL,
    cantidad int NOT NULL,
    PRIMARY KEY (codigo_historial_stock),
    FOREIGN KEY (producto) REFERENCES productos(codigo_producto)
);

INSERT INTO historial_stock (fecha, referencia, producto, cantidad) VALUES
('2023-11-20 19:59:00', 'PEDIDO 1', 1, 100),
('2023-11-20 19:59:00', 'PEDIDO 1', 4, 50),
('2023-11-20 20:00:00', 'PEDIDO 2', 1, 10),
('2023-11-20 20:00:00', 'VENTA 1', 1, -5),
('2023-11-20 20:00:00', 'VENTA 1', 4, 1);

SELECT * FROM categorias;
SELECT * FROM tipo_documentos;
SELECT * FROM categorias_unidad_medida;
SELECT * FROM unidades_medida;
SELECT * FROM proveedores;
SELECT * FROM productos;
SELECT * FROM cabecera_pedidos;
SELECT * FROM estados_pedido;
SELECT * FROM detalle_pedidos;
SELECT * FROM cabecera_ventas;
SELECT * FROM detalle_ventas;

