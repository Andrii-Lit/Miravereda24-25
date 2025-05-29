package es.ieslavereda.miravereda.Model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import java.time.LocalDateTime;

public class Cliente implements Serializable {
    private int id;
    private String contrasenya;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String cod_postal;
    private String email;
    private Date fecha_nac;
    private String num_tarjeta;
    private LocalDateTime changedTs;

    public int getId() {
        return id;
    }
    public Cliente(){

    }

    public Cliente(String email, String contrasenya) {
        this.email = email;
        this.contrasenya = contrasenya;

    }

    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public void setChangedTs(LocalDateTime changedTs) {
        this.changedTs = changedTs;
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

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", contrasenya='" + contrasenya + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", cod_postal='" + cod_postal + '\'' +
                ", email='" + email + '\'' +
                ", fecha_nac=" + fecha_nac +
                ", num_tarjeta='" + num_tarjeta + '\'' +
                ", changedTs=" + changedTs +
                '}';
    }
}
