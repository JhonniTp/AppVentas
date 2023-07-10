package Comprobante;

import java.time.LocalDate;
import java.util.Map;

import Entidades.CarritoCompra;
import Entidades.Producto;
//import Entidades.Usuario;

public class Factura {
    public static void generarFactura(CarritoCompra carrito, Map<String, Double> descuentosUsuarios) {
        System.out.println("           SHOPSALE");
        System.out.println("----- Factura de compra -----");
        System.out.println("Usuario: " + carrito.getUsuario().getCodigo());
        System.out.println("CAJA N - 1/Samanta Gutierrez");
        System.out.println("Fecha: " + LocalDate.now());
        System.out.println("-----------------------------");
        System.out.println("-----LISTA DE PRODUCTOS-----");

        Map<Producto, Integer> productosAgrupados = carrito.getProductosAgrupados();
        double descuentoTotal = 0.0;
        double precioTotal = 0.0;

        for (Map.Entry<Producto, Integer> entry : productosAgrupados.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            double descuento = descuentosUsuarios.getOrDefault(carrito.getUsuario().getCodigo(), 0.0);
            double precioUnitario = producto.getPrecio() * cantidad;
            double descuentoAplicado = precioUnitario * (descuento / 100.0);
            double precioFinal = precioUnitario - descuentoAplicado;

            System.out.println("Producto: " + producto.getNombre());
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Descuento aplicado: S/" + descuentoAplicado);
            System.out.println("Precio final: S/" + precioFinal);
            System.out.println("-----------------------------");

            descuentoTotal += descuentoAplicado;
            precioTotal += precioFinal;
        }

        System.out.println("Descuento total: S/" + descuentoTotal);
        System.out.println("Precio total: S/" + precioTotal);
        System.out.println("-----------------------------");

        double igv = precioTotal * 0.18; // 18% de IGV
        double precioFinalConIGV = precioTotal + igv;

        System.out.println("IGV (18%): S/" + igv);
        System.out.println("Precio final (con IGV): S/" + precioFinalConIGV);
        System.out.println("-----------------------------");
        System.out.println("\n ¡¡GRACIAS POR TU COMPRA!!");
        System.out.println(" ");
    }
}

