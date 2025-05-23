package Model;


import java.sql.Date;
import java.time.LocalDate;

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
    private LocalDate changedTs;

    public Cliente(int id, String contrasenya, String nombre, String apellidos, String domicilio, String cod_postal, String email, Date fecha_nac, String num_tarjeta) {
        this.id = id;
        this.contrasenya = contrasenya;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.cod_postal = cod_postal;
        this.email = email;
        this.fecha_nac = fecha_nac;
        this.num_tarjeta = num_tarjeta;
        changedTs=LocalDate.now();
    }

    public int getId() {
        return id;
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

    public LocalDate getChangedTs() {
        return changedTs;
    }
}
