package tpo1.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import tpo1.models.Pedido;

public class TiendaOnline {
    private ExecutorService executor;

    public TiendaOnline() {
        this.executor = Executors.newFixedThreadPool(10); // Pool de 10 hilos
    }

    public Future<Pedido> procesarPedido(Pedido pedido) {
        ProcesadorDePedidos procesador = new ProcesadorDePedidos(pedido);
        return executor.submit(procesador); // Procesa el pedido en uno de los hilos disponibles
    }

    public void shutdown() {
        executor.shutdown();
    }
}
