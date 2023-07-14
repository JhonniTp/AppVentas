package Entidades;
//import java.time.LocalDate;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Comprobante.Factura;
import Comprobante.Recibo;
import Repositorio.ValidacionUsuario;

public class TiendaVentas {
    private Map<String, Usuario> usuariosRegistrados;
    private Map<String, Double> descuentosUsuarios;
    private Map<String, Producto> inventario;
    private CarritoCompra carrito;
    private Scanner scanner;
    private String codigoUsuario;
    private ValidacionUsuario validar;
    //private Map<String, String> nombresUsuarios;
    //private List<Categoria> categorias;


    public TiendaVentas() {
        //se creaion de un nuevo objeto 'hashmap' vacio
        //nombresUsuarios = new HashMap<>();
        validar = new ValidacionUsuario(this);
        usuariosRegistrados = new HashMap<>();
        descuentosUsuarios = new HashMap<>();
        inventario = new HashMap<>();
        carrito = new CarritoCompra();
        scanner = new Scanner(System.in);

        Producto manzanas = new Producto("1", "Manzanas", 1.0, 20);
        Producto gaseosa = new Producto("2", "Gaseosas", 1.00, 50);
        Producto chocolate = new Producto("3", "Chocolates", 2.00, 40);
        Producto papas = new Producto("4", "Papas", 2.00, 40);
        Producto Incakola = new Producto("5", "Incakola", 1.0, 20);
        Producto Fanta = new Producto("6", "Fanta", 1.00, 50);
        Producto Cocacola = new Producto("7", "Cocacola", 2.00, 40);
        Producto Redbull = new Producto("8", "Redbull", 2.00, 40);

        //se agregan los productos al inventario (hashmap)
        inventario.put(manzanas.getId(), manzanas);
        inventario.put(gaseosa.getId(), gaseosa);
        inventario.put(chocolate.getId(), chocolate);
        inventario.put(papas.getId(), papas);
        inventario.put(Incakola.getId(), Incakola);
        inventario.put(Fanta.getId(), Fanta);
        inventario.put(Cocacola.getId(), Cocacola);
        inventario.put(Redbull.getId(), Redbull);

        
    }

    //Método para registrar a un nuevo usuario
    public boolean iniciarSesion() {
        //usuario predeterminado
        Usuario usuarioPredeterminado = new Usuario("Yoni Nando","Yoni Nando", "Turpo","12345");
        descuentosUsuarios.put(usuarioPredeterminado.getCodigo(), 20.0);

        System.out.println("Ingrese su código de usuario:");
        String codigoUsuario = scanner.nextLine();
        
        if (descuentosUsuarios.containsKey(codigoUsuario)) {
            System.out.println("Ingrese su contraseña:");
            String contraseña = scanner.nextLine();
            Usuario usuario = new Usuario(codigoUsuario, "", "", contraseña);
            
            if (usuarioPredeterminado.getCodigo().equals(codigoUsuario) && usuarioPredeterminado.getContraseña().equals(contraseña)) {
                System.out.println("¡BIENVENIDO " + usuarioPredeterminado.getNombreu() + "!");
                this.codigoUsuario = codigoUsuario;
                return true;
            }
            
            if (validar.validarCredenciales(usuario, contraseña)) {
                System.out.println("¡BIENVENIDO " + usuario.getNombreu() + "!");
                this.codigoUsuario = codigoUsuario;
                return true;
            } else {
                System.out.println("Contraseña incorrecta. Inicio de sesión fallido.");
            }
        } else {
            System.out.println("Usuario no encontrado. Regístrese para crear una cuenta.");
        }
        
        return false;
    }
    
     //Modulo de obtencion de descuento para el usuario
     private double obtenerDescuentoConsola() {
        double descuento = -1;
        while (descuento < 0) {
            try {
                String input = scanner.nextLine();
                descuento = Double.parseDouble(input);
                if (descuento < 0) {
                    System.out.println("El descuento debe ser un valor positivo. Ingrese nuevamente:");
                }
            } catch (NumberFormatException e) {
                System.out.println("El descuento debe ser un valor numérico. Ingrese nuevamente:");
            }
        }
        return descuento;
    }
    //Metodo de registrar usuario nuevo
    public void registrarUsuario() {
        System.out.println("Ingrese nombre de usuario:");
        String codigoUsuario = scanner.nextLine();
        if (usuariosRegistrados.containsKey(codigoUsuario)) {
            System.out.println("El usuario ya existe. Por favor, inicie sesión o elija otro código de usuario.");
        } else {
            System.out.println("Ingrese su nombre:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese su apellido:");
            String apellido = scanner.nextLine();
            System.out.println("Ingrese una contraseña:");
            String contraseña = scanner.nextLine();
    
            // Verificar si la contraseña cumple con los requisitos deseados
            if (contraseña.equals("12345")) {
                System.out.println("Contraseña no válida. Debe ser diferente a '12345'.");
                return; // Salir del método sin registrar al usuario
            }
    
            System.out.println("Ingrese el porcentaje de descuento para su usuario (sin el símbolo '%'):");
            double descuento = obtenerDescuentoConsola();
    
            System.out.println("Usuario nuevo registrado. YA PUEDES INICIAR SECION");
            Usuario usuario = new Usuario(codigoUsuario, nombre, apellido, contraseña); // Asignar correctamente la contraseña
            usuariosRegistrados.put(codigoUsuario, usuario);
            descuentosUsuarios.put(codigoUsuario, descuento);
    
            // Actualizar el código de usuario actual
            this.codigoUsuario = codigoUsuario;
        }
    }
    public Map<String, Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }
    

    //Método de productos del inventario recorrer
    public void mostrarInventario() {
        System.out.println("----- Inventario de productos -----");
        for (Producto producto : inventario.values()) {
            System.out.println(producto.getId() + " | " + producto.getNombre() + " | Precio: s/" + producto.getPrecio() + " | Cantidad disponible: " + producto.getCantidad());
        }
        System.out.println("-----------------------------------");
    }

    //Modulo de cantidad para el producto
    private int obtenerCantidadConsola() {
        int cantidad = -1;
        while (cantidad < 0) {
            try {
                String input = scanner.nextLine();
                cantidad = Integer.parseInt(input);
                if (cantidad < 0) {
                    System.out.println("La cantidad debe ser un valor positivo. Ingrese nuevamente:");
                }
            } catch (NumberFormatException e) {
                System.out.println("La cantidad debe ser un valor numérico. Ingrese nuevamente:");
            }
        }
        return cantidad;
    }
    //validación y selección de productos a traves de su ID 
    public void seleccionarProductos() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("Ingrese el ID del producto que desea agregar al carrito (0 para finalizar):");
            String idProducto = scanner.nextLine();
            if (idProducto.equals("0")) {
                continuar = false;
            } else {
                Producto producto = inventario.get(idProducto);
                if (producto != null) {
                    System.out.println("Ingrese la cantidad del producto que desea agregar:");
                    int cantidad = obtenerCantidadConsola();
                    if (cantidad > producto.getCantidad()) {
                        System.out.println("La cantidad ingresada excede la cantidad disponible en el inventario. Se ajustará al máximo disponible.");
                        cantidad = producto.getCantidad();
                    }
                    carrito.agregarProducto(producto, cantidad);
                } else {
                    System.out.println("El producto ingresado no existe en el inventario.");
                }
            }
        }
        carrito.setUsuario(new Usuario(codigoUsuario, codigoUsuario, codigoUsuario, codigoUsuario));
    }

    //Se realiza las operaciones de compras y detallando los pagos totales a realizar.
    public void mostrarDetallesCompra() {
        System.out.println("----- Detalles de la compra -----");
        Map<Producto, Integer> productosAgrupados = carrito.getProductosAgrupados();
        double descuentoTotal = 0.0;
        double precioTotal = 0.0;

        if (productosAgrupados == null || productosAgrupados.isEmpty()) {
            System.out.println("No hay productos en el carrito de compra.");
            System.out.println("---------------------------------");
            return;
        }

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
            System.out.println("---------------------------------");

            descuentoTotal += descuentoAplicado;
            precioTotal += precioFinal;
        }

        System.out.println("Descuento total: S/" + descuentoTotal);
        System.out.println("Precio total: S/" + precioTotal);
        System.out.println("---------------------------------");
    }

    //modulo de proceso de pago 
    public void procesarPago() {
        double descuento = descuentosUsuarios.getOrDefault(carrito.getUsuario().getCodigo(), 0.0);
        double precioTotal = carrito.getTotal();
        double descuentoAplicado = precioTotal * (descuento / 100.0);
        double precioFinal = precioTotal - descuentoAplicado;
        double igv = precioFinal * 0.18; // 18% de IGV

        System.out.println("----- Pago -----");
        System.out.println("Precio total: S/" + precioTotal);
        System.out.println("Descuento aplicado: S/" + descuentoAplicado);
        System.out.println("Precio final (sin IGV): S/" + precioFinal);
        System.out.println("IGV (18%): S/" + igv);
        System.out.println("Precio final (con IGV): S/" + (precioFinal + igv));
        System.out.println("-----------------");

        System.out.println("¿Desea recibir un recibo de pago o una factura? (R/F)");
        String opcion = scanner.nextLine();
        if (opcion.equalsIgnoreCase("R")) {
            Recibo.generarRecibo(carrito, descuentosUsuarios);
        } else if (opcion.equalsIgnoreCase("F")) {
            Factura.generarFactura(carrito, descuentosUsuarios);
        } else {
            System.out.println("Opción inválida. No se generará ningún documento adicional.");
        }
    }

    //Metodo vaciar carrito
    public void vaciarCarrito() {
        carrito.vaciarCarrito();
        System.out.println("Carrito vaciado.");
    }
}
