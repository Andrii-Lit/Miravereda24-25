package es.ieslavereda.miravereda.Model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Cliente {
    private String contrasenya, nombre, apellidos, domicilio,
            cod_postal, email, num_tarjeta;
    private Date fecha_nac;
    private LocalDateTime changedTs;

    public Cliente(String email, String contrasenya){
        this.email = email;
        this.contrasenya = contrasenya;
        this.changedTs = LocalDateTime.now();
    }
    public Cliente(String contrasenya, String nombre, String apellidos,
                   String domicilio, String cod_postal, String email, Date fecha_nac, String num_tarjeta) {
        this.contrasenya = contrasenya;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.cod_postal = cod_postal;
        this.email = email;
        this.fecha_nac = fecha_nac;
        this.num_tarjeta = num_tarjeta;
        this.changedTs = LocalDateTime.now();
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public String getEmail() {
        return email;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public LocalDateTime getChangedTs() {
        return changedTs;
    }
}
