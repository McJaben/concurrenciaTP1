package tpo1.services;

import java.util.concurrent.Callable;
import tpo1.models.EstadoPedido;
import tpo1.models.Pedido;


public class ProcesadorDePedidos implements Callable<Pedido> {
    private Pedido pedido;

    public ProcesadorDePedidos(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public Pedido call() throws Exception {
        pedido.setEstado(EstadoPedido.PROCESANDO);

        // Simulación de procesamiento
        Thread.sleep(2000);

        // Cambiando el estado a COMPLETADO o FALLIDO aleatoriamente
        if (Math.random() > 0.5) {
            pedido.setEstado(EstadoPedido.COMPLETADO);
        } else {
            pedido.setEstado(EstadoPedido.FALLIDO);
        }

        return pedido;
    }
}
