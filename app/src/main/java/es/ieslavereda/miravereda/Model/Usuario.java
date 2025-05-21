package es.ieslavereda.miravereda.Model;

import java.sql.Timestamp;

public class Usuario {
    private int id;
    private String contrasenya;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String cod_postal;
    private String email;
    private long fecha_nac;
    private String num_tarjeta;
    private Timestamp changedTs;

    public int getId() {
        return id;
    }

    public Usuario(String email, String contrasenya) {
        this.email = email;
        this.contrasenya = contrasenya;

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

    public long getFecha_nac() {
        return fecha_nac;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public Timestamp getChangedTs() {
        return changedTs;
    }
}
