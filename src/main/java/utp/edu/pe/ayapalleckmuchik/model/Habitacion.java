package utp.edu.pe.ayapalleckmuchik.model;

public class Habitacion {
    private int id_habitacion;
    private String numero_habitacion;
    private int id_tipo_habitacion;
    private String estado;
    private String estado_limpieza;
    private Tipo_habitacion tipo_habitacion;


    public Habitacion() {
    }

    public Habitacion(int id_habitacion, String numero_habitacion, int id_tipo_habitacion, String estado, String estado_limpieza) {
        this.id_habitacion = id_habitacion;
        this.numero_habitacion = numero_habitacion;
        this.id_tipo_habitacion = id_tipo_habitacion;
        this.estado = estado;
        this.estado_limpieza = estado_limpieza;
    }

    public Habitacion(String numeroHabitacion, int tipoHabitacion, String estado, String estadoLimpieza) {
        this.numero_habitacion = numeroHabitacion;
        this.id_tipo_habitacion = tipoHabitacion;
        this.estado = estado;
        this.estado_limpieza = estadoLimpieza;
    }

    public Habitacion(int id_habitacion, String numero_habitacion, String estado, String estado_limpieza, Tipo_habitacion tipo_habitacion) {
        this.id_habitacion = id_habitacion;
        this.numero_habitacion = numero_habitacion;
        this.estado = estado;
        this.estado_limpieza = estado_limpieza;
        this.tipo_habitacion = tipo_habitacion;
    }

    public Habitacion(String numero_habitacion, String estado, String estado_limpieza, Tipo_habitacion tipo_habitacion) {
        this.numero_habitacion = numero_habitacion;
        this.estado = estado;
        this.estado_limpieza = estado_limpieza;
        this.tipo_habitacion = tipo_habitacion;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public String getNumero_habitacion() {
        return numero_habitacion;
    }

    public void setNumero_habitacion(String numero_habitacion) {
        this.numero_habitacion = numero_habitacion;
    }

    public int getId_tipo_habitacion() {
        return id_tipo_habitacion;
    }

    public void setId_tipo_habitacion(int id_tipo_habitacion) {
        this.id_tipo_habitacion = id_tipo_habitacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado_limpieza() {
        return estado_limpieza;
    }

    public void setEstado_limpieza(String estado_limpieza) {
        this.estado_limpieza = estado_limpieza;
    }

    public Tipo_habitacion getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(Tipo_habitacion tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }
}
