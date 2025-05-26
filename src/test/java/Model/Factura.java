package Model;

import java.sql.Date;
import java.time.LocalDate;

public class Factura {
    private int id;
    private int cliente_id;
    private double total_con_iva;
    private double total;
    private double decimal;
    private Date fecha;
    private LocalDate changedTS;

    public Factura(int id, int cliente_id, double total_con_iva, double total, double decimal, Date fecha) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.total_con_iva = total_con_iva;
        this.total = total;
        this.decimal = decimal;
        this.fecha = fecha;
        changedTS = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public double getTotal() {
        return total;
    }

    public double getTotal_con_iva() {
        return total_con_iva;
    }

    public double getDecimal() {
        return decimal;
    }

    public Date getFecha() {
        return fecha;
    }

    public LocalDate getChangedTS() {
        return changedTS;
    }
}
