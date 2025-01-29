package utp.edu.pe.ayapalleckmuchik.model;

import utp.edu.pe.ayapalleckmuchik.model.enums.Estado;

import java.time.LocalDateTime;

public class Solicitud {
    private int id_solicitud;
    private int id_tipo_habitacion;
    private LocalDateTime fecha_solicitud;
    private LocalDateTime fecha_reserva;
    private String nombre_cliente;
    private String correo;
    private int duracion;
    private Estado estado;
    private Tipo_habitacion tipoHabitacion;

    public Solicitud() {
    }

    public Solicitud(int id_solicitud, int id_tipo_habitacion, LocalDateTime fecha_solicitud, LocalDateTime fecha_reserva, String nombre_cliente, String correo, int duracion, Estado estado) {
        this.id_solicitud = id_solicitud;
        this.id_tipo_habitacion = id_tipo_habitacion;
        this.fecha_solicitud = fecha_solicitud;
        this.fecha_reserva = fecha_reserva;
        this.nombre_cliente = nombre_cliente;
        this.correo = correo;
        this.duracion = duracion;
        this.estado = estado;
    }

    public Solicitud(int id_tipo_habitacion, LocalDateTime fecha_solicitud, LocalDateTime fecha_reserva, String nombre_cliente, String correo, int duracion, Estado estado) {
        this.id_tipo_habitacion = id_tipo_habitacion;
        this.fecha_solicitud = fecha_solicitud;
        this.fecha_reserva = fecha_reserva;
        this.nombre_cliente = nombre_cliente;
        this.correo = correo;
        this.duracion = duracion;
        this.estado = estado;
    }


    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public int getId_tipo_habitacion() {
        return id_tipo_habitacion;
    }

    public void setId_tipo_habitacion(int id_tipo_habitacion) {
        this.id_tipo_habitacion = id_tipo_habitacion;
    }

    public LocalDateTime getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(LocalDateTime fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public LocalDateTime getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(LocalDateTime fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Tipo_habitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(Tipo_habitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
}
