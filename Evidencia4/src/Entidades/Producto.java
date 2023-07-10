package Entidades;
public class Producto {

    //Definición de variables con la clave private
    //esto para restringir el acceso de las demas clases
    private String id;
    private String nombre;
    private double precio;
    private int cantidad;

    //Constructor de la clase Producto.
    //Crea una nueva instancia de Producto con los valores proporcionados.
    public Producto(String id, String nombre, double precio, int cantidad) {

        //la palabra clave this para hacer referencia a la 
        //instancia actual de la clase en la que se encuentra el código
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    //obtiene y retorna el id del producto
    public String getId() {
        return id;
    }

    //Obtiene y retorna el nombre del producto
    public String getNombre() {
        return nombre;
    }

    //Obtiene y retorna el precio del producto
    public double getPrecio() {
        return precio;
    };

    //Obtiene y retorna la cantidad disponible del producto
    public int getCantidad() {
        return cantidad;
    }
}

