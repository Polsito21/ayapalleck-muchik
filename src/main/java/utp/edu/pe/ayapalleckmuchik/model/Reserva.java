package utp.edu.pe.ayapalleckmuchik.model;

import java.time.LocalDateTime;

public class Reserva {
    private int id_reserva;
    private int id_cliente;
    private int id_habitacion;
    private double monto_total; // Campo agregado
    private String metodo_pago; // Nuevo campo agregado
    private LocalDateTime fecha_ingreso;
    private LocalDateTime fecha_salida;
    private int id_admin;
    private String estado_reserva;
    private Cliente cliente;
    private Habitacion habitacion;
    private Administrador admin;

    public Reserva(int id_cliente, int id_habitacion, double monto_total, String metodo_pago, LocalDateTime fecha_ingreso, LocalDateTime fecha_salida, int id_admin, String estado_reserva) {
        this.id_cliente = id_cliente;
        this.id_habitacion = id_habitacion;
        this.monto_total = monto_total;
        this.metodo_pago = metodo_pago;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_salida = fecha_salida;
        this.id_admin = id_admin;
        this.estado_reserva = estado_reserva;
    }

    public Reserva(int id_reserva, int id_cliente, int id_habitacion, double monto_total, String metodo_pago, LocalDateTime fecha_ingreso, LocalDateTime fecha_salida, int id_admin, String estado_reserva) {
        this.id_reserva = id_reserva;
        this.id_cliente = id_cliente;
        this.id_habitacion = id_habitacion;
        this.monto_total = monto_total;
        this.metodo_pago = metodo_pago;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_salida = fecha_salida;
        this.id_admin = id_admin;
        this.estado_reserva = estado_reserva;
    }

    public Reserva() {
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "id_reserva=" + id_reserva +
                ", id_cliente=" + id_cliente +
                ", id_habitacion=" + id_habitacion +
                ", monto_total=" + monto_total +
                ", metodo_pago='" + metodo_pago + '\'' +
                ", fecha_ingreso=" + fecha_ingreso +
                ", fecha_salida=" + fecha_salida +
                ", id_admin=" + id_admin +
                ", estado_reserva='" + estado_reserva + '\'' +
                '}';
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public LocalDateTime getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDateTime fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public LocalDateTime getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(LocalDateTime fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getEstado_reserva() {
        return estado_reserva;
    }

    public void setEstado_reserva(String estado_reserva) {
        this.estado_reserva = estado_reserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
}
