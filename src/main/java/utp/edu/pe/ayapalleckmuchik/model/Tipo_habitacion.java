package utp.edu.pe.ayapalleckmuchik.model;

public class Tipo_habitacion {
    // Atributos de la clase
    private int id_tipo_habitacion;   // ID del tipo de habitación
    private String nombre_habitacion;  // Nombre de la habitación
    private String descripcion;        // Descripción de la habitación
    private double precio_noche;       // Precio por noche de la habitación

    // Constructor con parámetros
    public Tipo_habitacion(int id_tipo_habitacion, String nombre_habitacion, String descripcion, double precio_noche) {
        this.id_tipo_habitacion = id_tipo_habitacion;
        this.nombre_habitacion = nombre_habitacion;
        this.descripcion = descripcion;
        this.precio_noche = precio_noche;
    }

    public Tipo_habitacion(String nombre_habitacion, String descripcion, double precio_noche) {
        this.nombre_habitacion = nombre_habitacion;
        this.descripcion = descripcion;
        this.precio_noche = precio_noche;
    }

    // Constructor por defecto
    public Tipo_habitacion() {
    }

    @Override
    public String toString() {
        return "Tipo_habitacion{" +
                "id_tipo_habitacion=" + id_tipo_habitacion +
                ", nombre_habitacion='" + nombre_habitacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio_noche=" + precio_noche +
                '}';
    }

    // Métodos Getter y Setter
    public int getId_tipo_habitacion() {
        return id_tipo_habitacion;
    }

    public void setId_tipo_habitacion(int id_tipo_habitacion) {
        this.id_tipo_habitacion = id_tipo_habitacion;
    }

    public String getNombre_habitacion() {
        return nombre_habitacion;
    }

    public void setNombre_habitacion(String nombre_habitacion) {
        this.nombre_habitacion = nombre_habitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_noche() {
        return precio_noche;
    }

    public void setPrecio_noche(double precio_noche) {
        this.precio_noche = precio_noche;
    }
}
