package Entidades;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarritoCompra {
    private List<Producto> productos;
    private Usuario usuario;

    //Constructor y inicializador de las clase Productos y Usuario
    public CarritoCompra() {
        this.productos = new ArrayList<>();
        this.usuario = null;
    }

    //Método de agregar productos al carrito y toma como argumento el producto y cantidad
    public void agregarProducto(Producto producto, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            productos.add(producto);
        }
    }

    //Método que devuelve los productos que estan en el carrito
    public List<Producto> getProductos() {
        return productos;
    }

    //Método de vaciar el carrito
    public void vaciarCarrito() {
        productos.clear();
    }

    //Método que devuelve el total de lospreductos que estan en el carrito
    public double getTotal() {
        double total = 0.0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        return total;
    }

    //Método que asigna un objeto Usuario al carrito de compra
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //Método que devuelve el objeto Usuario asociado al carrito de compra
    public Usuario getUsuario() {
        return usuario;
    }

    //Método devuelve un Map que contiene los productos del carrito agrupados por su cantidad
    public Map<Producto, Integer> getProductosAgrupados() {
        Map<Producto, Integer> productosAgrupados = new HashMap<>();
        for (Producto producto : productos) {
            int cantidad = productosAgrupados.getOrDefault(producto, 0);
            productosAgrupados.put(producto, cantidad + 1);
        }
        return productosAgrupados;
    }
    
    
}



