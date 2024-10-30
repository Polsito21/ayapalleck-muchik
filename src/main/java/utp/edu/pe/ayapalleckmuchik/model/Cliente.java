package utp.edu.pe.ayapalleckmuchik.model;

import java.time.LocalDate;

public class Cliente {
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String tipo_documento;
    private String numero_documento;
    private String email;
    private String telefono;
    private LocalDate fecha_nacimiento;


  public Cliente(int id_cliente, String nombre, String apellido, String tipo_documento, String numero_documento, String email, String telefono, LocalDate fecha_nacimiento) {
      this.id_cliente = id_cliente;
      this.nombre = nombre;
      this.apellido = apellido;
      this.tipo_documento = tipo_documento;
      this.numero_documento = numero_documento;
      this.email = email;
      this.telefono = telefono;
      this.fecha_nacimiento = fecha_nacimiento;
  }

    public Cliente(String nombre, String apellido, String tipo_documento, String numero_documento, String email, String telefono, LocalDate fecha_nacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
        this.email = email;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Cliente() {

  }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

        @Override
        public String toString() {
            return "Cliente{" +
                    "id_cliente=" + id_cliente +
                    ", nombre='" + nombre + '\'' +
                    ", apellido='" + apellido + '\'' +
                    ", tipo_documento='" + tipo_documento + '\'' +
                    ", numero_documento='" + numero_documento + '\'' +
                    ", email='" + email + '\'' +
                    ", telefono='" + telefono + '\'' +
                    ", fecha_nacimiento=" + fecha_nacimiento +
                    '}';
        }
    }



