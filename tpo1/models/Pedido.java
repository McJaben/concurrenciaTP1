package tpo1.models;

import tpo1.services.Notificador;
import java.util.ArrayList;
import java.util.List;


public class Pedido {
    private String id;
    private String cliente;
    private EstadoPedido estado;
    private List<Notificador> observadores;

    public Pedido(String id, String cliente) {
        this.id = id;
        this.cliente = cliente;
        this.estado = EstadoPedido.PENDIENTE;
        this.observadores = new ArrayList<>();
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        notificarObservadores();
    }

    public void registrarObservador(Notificador observador) {
        observadores.add(observador);
    }

    private void notificarObservadores() {
        for (Notificador observador : observadores) {
            observador.actualizar(this);
        }
    }
    
    // Getters para id, cliente y estado

    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    
}
