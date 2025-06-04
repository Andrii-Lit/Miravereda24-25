package com.ieslavereda.es.miravereda.Model;


import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * @author Andrii,Cristobal,Dario,Leonardo
 * @version 1.0
 *
 */
@Getter
@Builder
public class Cliente {
    private int id;
    private String contrasenya;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String cod_postal;
    private String email;
    private Date fecha_nac;
    private String num_tarjeta;




}
