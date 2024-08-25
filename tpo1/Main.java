package tpo1;

import tpo1.models.Pedido;
import tpo1.services.TiendaOnline;
import tpo1.services.Notificador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        TiendaOnline tienda = new TiendaOnline();
        List<Pedido> pedidos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;
        int cantPedidos = 0;
        while (continuar) {
            System.out.println("=== Menú de Tienda Online ===");
            System.out.println("1. Crear un nuevo pedido");
            System.out.println("2. Procesar todos los pedidos");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opción: ");
            int opcion = scanner.nextInt(); // lee un entero ingresado por el usuario
            scanner.nextLine(); // Para limpiar el buffer

            switch (opcion) {
                case 1:
                    cantPedidos++;
                    String id = String.valueOf(cantPedidos);
                    System.out.println("Ingrese el nombre del cliente: ");
                    String cliente = scanner.nextLine();

                    // Crear un nuevo pedido
                    Pedido pedido = new Pedido(id, cliente);

                    // Crear y registrar un observador único para este pedido
                    pedido.registrarObservador(
                            p -> System.out.println("Pedido " + p.getId() + " está " + p.getEstado()));
                    
                    // Agregar el pedido a la lista
                    pedidos.add(pedido);

                    System.out.println("Pedido creado exitosamente. \n");
                    break;
                
                case 2:
                    System.out.println("Procesando pedidos... \n");
                    List<Future<Pedido>> futures = new ArrayList<>();

                    // Procesar todos los pedidos en paralelo
                    for (Pedido p : pedidos) {
                        Future<Pedido> future = tienda.procesarPedido(p);
                        futures.add(future);
                    }

                    // Esperar a que todos los pedidos sean procesados
                    for (Future<Pedido> future : futures) {
                        try {
                            future.get(); // Bloquea hasta que el pedido haya sido procesado
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("Todos los pedidos han sido procesados. \n");
                    break;
                
                case 3:
                    continuar = false;
                    System.out.println("Saliendo del sistema.");
                    break;
                
                default:
                    System.out.println("Opción no válida, intente nuevamente. \n");
                    break;
            }
        }

        // Cerrar el ExecutorService antes de salir
        tienda.shutdown();
        scanner.close();

        // // Simulación de múltiples pedidos
        // Pedido pedido1 = new Pedido("1", "Juan Perez");
        // Pedido pedido2 = new Pedido("2", "Ana Gomez");
        // Pedido pedido3 = new Pedido("3", "Carlos Lopez");

        // // Crear y registrar observadores específicos para cada pedido
        // pedido1.registrarObservador(
        //         pedido -> System.out.println("Pedido " + pedido.getId() + " está " + pedido.getEstado()));
        // pedido2.registrarObservador(
        //         pedido -> System.out.println("Pedido " + pedido.getId() + " está " + pedido.getEstado()));
        // pedido3.registrarObservador(
        //         pedido -> System.out.println("Pedido " + pedido.getId() + " está " + pedido.getEstado()));

        // // Procesar pedidos concurrentemente
        // Future<Pedido> future1 = tienda.procesarPedido(pedido1);
        // Future<Pedido> future2 = tienda.procesarPedido(pedido2);
        // Future<Pedido> future3 = tienda.procesarPedido(pedido3);

        // try {
        //     future1.get(); // Espera a que el pedido1 sea procesado
        //     future2.get(); // Espera a que el pedido2 sea procesado
        //     future3.get(); // Espera a que el pedido3 sea procesado
        // } catch (InterruptedException | ExecutionException e) {
        //     e.printStackTrace();
        // } finally {
        //     tienda.shutdown();
        // }
    }
}
