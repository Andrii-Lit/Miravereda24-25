package es.ieslavereda.miravereda.Model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa un cliente de la plataforma.
 * Implementa Serializable para permitir su paso entre componentes.
 */
public class Cliente implements Serializable {
    private int id;
    private String contrasenya;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String cod_postal;
    private String email;
    private LocalDate fecha_nac;
    private String num_tarjeta;

    /**
     * Constructor completo con todos los campos excepto id.
     *
     * @param contrasenya Contraseña del cliente.
     * @param nombre Nombre del cliente.
     * @param apellidos Apellidos del cliente.
     * @param domicilio Dirección de domicilio.
     * @param cod_postal Código postal.
     * @param email Email del cliente.
     * @param fecha_nac Fecha de nacimiento.
     * @param num_tarjeta Número de tarjeta asociado.
     */
    public Cliente(String contrasenya, String nombre, String apellidos, String domicilio, String cod_postal, String email, LocalDate fecha_nac, String num_tarjeta) {
        this.contrasenya = contrasenya;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.cod_postal = cod_postal;
        this.email = email;
        this.fecha_nac = fecha_nac;
        this.num_tarjeta = num_tarjeta;
    }

    /**
     * Constructor vacío.
     */
    public Cliente() {
    }

    /**
     * Constructor con email y contraseña, útil para login.
     *
     * @param email Email del cliente.
     * @param contrasenya Contraseña del cliente.
     */
    public Cliente(String email, String contrasenya) {
        this.email = email;
        this.contrasenya = contrasenya;
    }

    /**
     * Obtiene el identificador único del cliente.
     *
     * @return Id del cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del cliente.
     *
     * @param id Id del cliente.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la contraseña del cliente.
     *
     * @return Contraseña.
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     * Establece la contraseña del cliente.
     *
     * @param contrasenya Contraseña.
     */
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombre Nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del cliente.
     *
     * @return Apellidos.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del cliente.
     *
     * @param apellidos Apellidos.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el domicilio del cliente.
     *
     * @return Domicilio.
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Establece el domicilio del cliente.
     *
     * @param domicilio Domicilio.
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * Obtiene el código postal.
     *
     * @return Código postal.
     */
    public String getCod_postal() {
        return cod_postal;
    }

    /**
     * Establece el código postal.
     *
     * @param cod_postal Código postal.
     */
    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    /**
     * Obtiene el email del cliente.
     *
     * @return Email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del cliente.
     *
     * @param email Email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la fecha de nacimiento.
     *
     * @return Fecha de nacimiento.
     */
    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    /**
     * Establece la fecha de nacimiento.
     *
     * @param fecha_nac Fecha de nacimiento.
     */
    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    /**
     * Obtiene el número de tarjeta asociado al cliente.
     *
     * @return Número de tarjeta.
     */
    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    /**
     * Establece el número de tarjeta asociado al cliente.
     *
     * @param num_tarjeta Número de tarjeta.
     */
    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }
}