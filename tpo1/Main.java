package tpo1;
import tpo1.models.Pedido;
import tpo1.services.TiendaOnline;
import tpo1.services.Notificador;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        TiendaOnline tienda = new TiendaOnline();

        Pedido pedido = new Pedido("1", "Juan Perez");
        Pedido pedido2 = new Pedido("2", "Pedro Rojas");
        Pedido pedido3 = new Pedido("3", "Leo Feliz");
        
        pedido.registrarObservador(new Notificador() {
            @Override
            public void actualizar(Pedido pedido) {
                System.out.println("Pedido " + pedido.getId() + " está " + pedido.getEstado());
            }
        });

        Future<Pedido> future = tienda.procesarPedido(pedido);

        try {
            future.get(); // Espera a que el pedido sea procesado
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            tienda.shutdown();
        }
    }
}
