-- DUMP PROCEDIMIENTOS Y TRIGGERS SQL
-- by Cristóbal

USE miravereda2425;

DELIMITER $$

-- #### FUNCIONES ####

DROP PROCEDURE IF EXISTS iniciar_sesion$$
CREATE PROCEDURE iniciar_sesion(OUT resultado BOOLEAN, IN p_email VARCHAR(100), IN p_contrasenya VARCHAR(255))
BEGIN
	DECLARE contra_correcta VARCHAR(255);
	SELECT contrasenya INTO contra_correcta FROM cliente WHERE email = p_email LIMIT 1;

	IF contra_correcta IS NULL THEN 
		SET resultado = FALSE;
	ELSEIF p_contrasenya = contra_correcta THEN 
		SET resultado = TRUE;
	ELSE 
		SET resultado = FALSE;
	END IF;
END$$

-- #### TABLA CLIENTE ####

DROP TRIGGER IF EXISTS trigger_cliente_carrito$$
CREATE TRIGGER trigger_cliente_carrito
AFTER INSERT ON cliente FOR EACH ROW
BEGIN
	INSERT INTO carrito(cliente_id) VALUES (NEW.id);
END$$

-- PROCEDIMIENTO para obtener CLIENTE por email

DROP PROCEDURE IF EXISTS get_cliente_por_email$$
CREATE PROCEDURE get_cliente_por_email(IN p_email VARCHAR(100))
BEGIN
	SELECT * FROM cliente WHERE email = p_email LIMIT 1;
END$$

-- #### TABLA VALORACION ####

DROP TRIGGER IF EXISTS trigger_valoracion_insert$$
CREATE TRIGGER trigger_valoracion_insert
BEFORE INSERT ON valoracion FOR EACH ROW
BEGIN
	IF NEW.valor > 10 THEN 
		SET NEW.valor = 10;
	ELSEIF NEW.valor < 0 THEN 
		SET NEW.valor = 0;
	END IF;
END$$

-- PROCEDIMIENTO para actualizar la puntuacion_media de CONTENIDO

DROP PROCEDURE IF EXISTS actualizar_nota$$
CREATE PROCEDURE actualizar_nota(IN p_cliente_id INT)
BEGIN
	DECLARE puntos_totales DECIMAL(10,2);
	DECLARE cantidad INT;
	DECLARE media DECIMAL(10,2);

	SELECT SUM(valor) INTO puntos_totales FROM valoracion WHERE contenido_id = p_cliente_id;
	SELECT COUNT(*) INTO cantidad FROM valoracion WHERE contenido_id = p_cliente_id;

	IF cantidad > 0 THEN 
		SET media = puntos_totales / cantidad;
	ELSE 
		SET media = 0;
	END IF;

	UPDATE contenido SET puntuacion_media = media WHERE id = p_cliente_id;
END$$

-- TRIGGER que llama a ACTUALIZAR_NOTA al hacer INSERT en VALORACION

DROP TRIGGER IF EXISTS trigger_actualizar_nota$$
CREATE TRIGGER trigger_actualizar_nota
AFTER INSERT ON valoracion FOR EACH ROW
BEGIN
	CALL actualizar_nota(NEW.contenido_id);
END$$

-- #### TABLA FACTURA ####

DROP PROCEDURE IF EXISTS comprar$$
CREATE PROCEDURE comprar(IN p_cliente_id INT)
BEGIN
	DECLARE carrito_total DECIMAL(10,2);
	DECLARE iva_rate DECIMAL(10,2) DEFAULT 0.21;
	DECLARE total_con_iva DECIMAL(10,2);
	DECLARE carrito_activo_id INT;

	-- obtener total del carrito activo
	SELECT id INTO carrito_activo_id FROM carrito WHERE cliente_id = p_cliente_id AND activo = TRUE;
	SELECT SUM(precio) INTO carrito_total FROM lin_fac WHERE carrito_id = carrito_activo_id;

	-- calcular total con IVA
	SET total_con_iva = carrito_total + (carrito_total * iva_rate);

	-- crear factura
	INSERT INTO factura(cliente_id, total, total_con_iva)
	VALUES (p_cliente_id, carrito_total, total_con_iva);

	-- desactivar el carrito
	UPDATE carrito SET activo = FALSE WHERE id = carrito_activo_id;

	-- crear un nuevo carrito
	INSERT INTO carrito(cliente_id) VALUES (p_cliente_id);
END$$

-- #### TABLA CARRITO ####

DROP PROCEDURE IF EXISTS actualizar_precio_carrito$$
CREATE PROCEDURE actualizar_precio_carrito(IN p_cliente_id INT)
BEGIN
	DECLARE carrito_activo_id INT;
	DECLARE precio_total DECIMAL(10,2);

	SELECT id INTO carrito_activo_id FROM carrito WHERE cliente_id = p_cliente_id AND activo = TRUE;
	SELECT SUM(precio) INTO precio_total FROM lin_fac WHERE carrito_id = carrito_activo_id;

	UPDATE carrito SET total = precio_total WHERE id = carrito_activo_id;
END$$

-- #### TABLA LIN_FAC ####

DROP PROCEDURE IF EXISTS anyadir_al_carrito$$
CREATE PROCEDURE anyadir_al_carrito(IN p_cliente_id INT, IN p_contenido_id INT)
BEGIN
	DECLARE carrito_activo_id INT;
	DECLARE contenido_precio DECIMAL(10,2);

	SELECT id INTO carrito_activo_id FROM carrito
	WHERE cliente_id = p_cliente_id AND activo = TRUE;

	IF p_contenido_id IN (SELECT contenido_id FROM pelicula) THEN
		SELECT precio INTO contenido_precio FROM pelicula WHERE contenido_id = p_contenido_id;
	ELSEIF p_contenido_id IN (SELECT contenido_id FROM serie) THEN
		SELECT precio INTO contenido_precio FROM serie WHERE contenido_id = p_contenido_id;
	ELSEIF p_contenido_id IN (SELECT contenido_id FROM corto) THEN
		SELECT precio INTO contenido_precio FROM corto WHERE contenido_id = p_contenido_id;
	ELSE 
		SET contenido_precio = 0.00;
	END IF;

	INSERT INTO lin_fac(carrito_id, contenido_id, precio) 
	VALUES (carrito_activo_id, p_contenido_id, contenido_precio);

	CALL actualizar_precio_carrito(p_cliente_id);
END$$

DELIMITER ;
