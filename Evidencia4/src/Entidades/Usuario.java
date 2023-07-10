package Entidades;
public class Usuario {
    private String codigo;
    private String nombre;
    private String apellido;
    private String pass;

    // Constructor de la clase Usuario
    public Usuario(String codigo, String nombre, String apellido, String pass) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pass = pass;
    }

    // Obtiene el código del usuario
    public String getCodigo() {
        return codigo;
    }

    public String getNombreu(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public String getContraseña(){
        return pass;
    }

    
}

