USE miraveredapractica;

-- #### FUNCIONES ####
DELIMITER $$

DROP PROCEDURE IF EXISTS iniciar_sesion$$
CREATE PROCEDURE iniciar_sesion(
    OUT resultado BOOLEAN, 
    IN p_email VARCHAR(100), 
    IN p_contrasenya VARCHAR(255)
)
BEGIN
    DECLARE contra_correcta VARCHAR(255);
    SELECT contrasenya INTO contra_correcta 
    FROM cliente 
    WHERE email = p_email 
    LIMIT 1;

    IF contra_correcta IS NULL THEN 
        SET resultado = FALSE;
    ELSEIF p_contrasenya = contra_correcta THEN 
        SET resultado = TRUE;
    ELSE 
        SET resultado = FALSE;
    END IF;
END$$

-- PROCEDIMIENTO para obtener CLIENTE por email
drop procedure if exists get_cliente_por_email$$

create procedure get_cliente_por_email(
    in p_email VARCHAR(100)
)
begin
    select * from cliente where email = p_email limit 1;
END$$

-- #### TRIGGER: INSERT CLIENTE => CREA CARRITO ####
DROP TRIGGER IF EXISTS trigger_cliente_carrito$$
CREATE TRIGGER trigger_cliente_carrito
AFTER INSERT ON cliente
FOR EACH ROW
BEGIN
    INSERT INTO carrito(cliente_id) VALUES (NEW.id);
END$$

-- #### TRIGGER: CONTROL DE VALOR EN VALORACION ####
DROP TRIGGER IF EXISTS trigger_valoracion_insert$$
CREATE TRIGGER trigger_valoracion_insert
BEFORE INSERT ON valoracion
FOR EACH ROW
BEGIN
    IF NEW.valor > 10 THEN 
        SET NEW.valor = 10;
    ELSEIF NEW.valor < 0 THEN 
        SET NEW.valor = 0;
    END IF;
END$$

-- #### PROCEDIMIENTO PARA ACTUALIZAR NOTA MEDIA ####
DROP PROCEDURE IF EXISTS actualizar_nota$$
CREATE PROCEDURE actualizar_nota(IN p_contenido_id INT)
BEGIN
    DECLARE puntos_totales DECIMAL(10,2);
    DECLARE cantidad INT;
    DECLARE media DECIMAL(10,2);

    SELECT SUM(valor) INTO puntos_totales 
    FROM valoracion 
    WHERE contenido_id = p_contenido_id;

    SELECT COUNT(*) INTO cantidad 
    FROM valoracion 
    WHERE contenido_id = p_contenido_id;

    IF cantidad > 0 THEN 
        SET media = puntos_totales / cantidad;
    ELSE 
        SET media = 0;
    END IF;

    UPDATE contenido 
    SET puntuacion_media = media 
    WHERE id = p_contenido_id;
END$$

-- #### TRIGGER: ACTUALIZAR NOTA TRAS VALORAR ####
DROP TRIGGER IF EXISTS trigger_actualizar_nota$$
CREATE TRIGGER trigger_actualizar_nota
AFTER INSERT ON valoracion
FOR EACH ROW
BEGIN
    CALL actualizar_nota(NEW.contenido_id);
END$$

-- #### PROCEDIMIENTO COMPRAR ####
DROP PROCEDURE IF EXISTS comprar$$
CREATE PROCEDURE comprar(IN p_cliente_id INT)
BEGIN
    DECLARE carrito_total DECIMAL(10,2);
    DECLARE iva_rate DECIMAL(10,2) DEFAULT 0.21;
    DECLARE total_con_iva DECIMAL(10,2);
    DECLARE carrito_id INT;

    -- Obtener ID del carrito activo
    SELECT id INTO carrito_id 
    FROM carrito 
    WHERE cliente_id = p_cliente_id AND activo = TRUE 
    LIMIT 1;

    -- Calcular total
    SELECT SUM(precio) INTO carrito_total 
    FROM lin_fac 
    WHERE carrito_id = carrito_id;

    IF carrito_total IS NULL THEN
        SET carrito_total = 0.00;
    END IF;

    SET total_con_iva = carrito_total + (carrito_total * iva_rate);

    -- Insertar factura
    INSERT INTO factura(cliente_id, total, total_con_iva)
    VALUES (p_cliente_id, carrito_total, total_con_iva);

    -- Desactivar carrito actual
    UPDATE carrito 
    SET activo = FALSE 
    WHERE id = carrito_id;

    -- Crear nuevo carrito
    INSERT INTO carrito(cliente_id) 
    VALUES (p_cliente_id);
END$$