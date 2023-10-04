//MODULO PRINCIPAL
//Interación con el usuario
//clase de ejecutable y de interaccion con el usuario
import java.util.Scanner;
import Entidades.TiendaVentas;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TiendaVentas tienda = new TiendaVentas();

        System.out.println("           SHOPSALE");
        System.out.println("¡Bienvenido al programa de ventas!");

        boolean ejecutar = true;
        while (ejecutar) {
            // Menú principal
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.println("---------------------------");
            System.out.println("Ingrese una opción:");

            int opcion = obtenerOpcionConsola(scanner);

            switch (opcion) {
                case 1:
                    boolean inicioSesionExitoso = tienda.iniciarSesion();
                    if (inicioSesionExitoso) {
                        System.out.println("Inicio de sesión exitoso. ¡Bienvenido!");

                        // Mostrar inventario
                        tienda.mostrarInventario();

                        // Selección de productos
                        tienda.seleccionarProductos();

                        // Mostrar detalles de la compra
                        tienda.mostrarDetallesCompra();

                        // Confirmación de compra
                        System.out.println("¿Desea continuar con la compra? (Si/No)");
                        String opcionCompra = scanner.nextLine();
                        if (opcionCompra.equalsIgnoreCase("Si")) {
                            // Procesar pago y generar recibo o factura
                            tienda.procesarPago();
                        } else {
                            System.out.println("La compra ha sido cancelada.");
                            tienda.vaciarCarrito();
                        }
                    }
                    break;
                case 2:
                    tienda.registrarUsuario();
                    break;
                case 3:
                    ejecutar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }

        scanner.close();
        System.out.println("¡Gracias por utilizar nuestro programa de ventas! Vuelva pronto.");
    }

    private static int obtenerOpcionConsola(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                int opcion = Integer.parseInt(input);
                return opcion;
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}
