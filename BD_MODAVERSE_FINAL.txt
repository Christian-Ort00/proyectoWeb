-- Crear base de datos
CREATE DATABASE IF NOT EXISTS modaverse;
USE modaverse;

-- Tabla: Categoria
CREATE TABLE Categoria (
    CategoriaID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100)
);

-- Tabla: Usuario
CREATE TABLE Usuario (
    UsuarioID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellidos VARCHAR(150) NOT NULL,
    Correo VARCHAR(150),
    Contrasena VARCHAR(100),
    Rol VARCHAR(50) NOT NULL
);

-- Tabla: Producto
CREATE TABLE Producto (
    ProductoID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(200) NOT NULL,
    Precio DECIMAL(10, 2) NOT NULL,
    Descripcion VARCHAR(200),
    CategoriaID INT,
    ruta_imagen VARCHAR(255),
    FOREIGN KEY (CategoriaID) REFERENCES Categoria(CategoriaID)
);

CREATE TABLE factura (
    id_factura int AUTO_INCREMENT PRIMARY KEY,
    id_usuario int NOT NULL,
    fecha DATETIME NOT NULL,
    total DOUBLE NOT NULL,
    estado INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(UsuarioID)
);

CREATE TABLE venta (
    id_venta int AUTO_INCREMENT PRIMARY KEY,
    id_factura int NOT NULL,
    id_producto int NOT NULL,
    precio DOUBLE NOT NULL,
    cantidad INT NOT NULL,
	FOREIGN KEY (id_factura) REFERENCES factura(id_factura),
   FOREIGN KEY (id_producto) REFERENCES Producto(ProductoID)
) ;

INSERT INTO Usuario (UsuarioID, Nombre, Apellidos, Correo, Contrasena, Rol) VALUES
(1, 'Juan',   'Castro Mora',    'j@gmail.com',   '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.', 'USER'),
(2, 'Rebeca', 'Contreras Mora', 'r@gmail.com','$2a$10$GkEj.ZzmQa/aEfDmtLIh3udIH5fMphx/35d0EYeqZL5uzgCJ0lQRi', 'USER'),
(3, 'Pedro',  'Mena Loria',     'p@gmail.com',     '$2a$10$koGR7eS22Pv5KdaVJKDcge04ZB53iMiw76.UjHPY.XyVYlYqXnPbO', 'ADMIN');

-- Insertar categorías
INSERT INTO Categoria (Nombre) VALUES 
('Mujer'), 
('Hombre');

-- Insertar productos
INSERT INTO Producto (Nombre, Descripcion, Precio, ruta_imagen, CategoriaID) VALUES
('Camiseta Oversize Blanca', 'Camiseta blanca de algodón estilo oversize.', 12000, 'camiseta.png', 2),
('Jeans Rectos Azules', 'Pantalón de mezclilla azul para uso casual.', 22000, 'jeans.png', 2),
('Tennis New Balance 550', 'Tennis deportivos estilo retro.', 40000, 'tennis.png', 2),
('Camisa Formal Celeste', 'Camisa manga larga para ocasiones formales.', 18000, 'camisaceleste.jpg', 2),
('Joggers Negros', 'Pantalón jogger para uso casual o deportivo.', 16000, 'joggersnegros.PNG', 2);

-- Insertar productos de la categoría Mujer (CategoriaID = 1)
INSERT INTO Producto (Nombre, Descripcion, Precio, ruta_imagen, CategoriaID) VALUES
('Vestido Floral Verano', 'Vestido ligero con estampado floral, ideal para verano.', 25000, 'vestidoverano.jpg', 1),
('Blusa Manga Larga Beige', 'Blusa elegante de manga larga en color beige.', 15000, 'blusa_beige.jpg', 1),
('Falda Plisada Negra', 'Falda plisada clásica en color negro.', 18000, 'falda_plisada.jpg', 1),
('Chaqueta de Cuero', 'Chaqueta estilo biker en cuero sintético.', 35000, 'chaqueta_cuero.png', 1),
('Sandalias Plataforma', 'Sandalias cómodas con plataforma media.', 20000, 'sandalias.JPG', 1);
