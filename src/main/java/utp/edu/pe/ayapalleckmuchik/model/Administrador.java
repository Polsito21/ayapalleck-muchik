package utp.edu.pe.ayapalleckmuchik.model;

public class Administrador {
    private int id_admin;
    private String usuario;
    private String password;

    // Constructor vacío
    public Administrador() {
    }

    // Constructor completo
    public Administrador(int idAdmin, String usuario, String password) {
        this.id_admin = idAdmin;
        this.usuario = usuario;
        this.password = password;
    }

    public Administrador(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    // Getters y Setters
    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "idAdmin=" + id_admin +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
