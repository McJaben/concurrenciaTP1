package tpo1.models;

import tpo1.services.Notificador;

public class Cliente implements Notificador {
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(Pedido pedido) {
        System.out.println("Hola " + nombre + ", tu pedido está: " + pedido.getEstado());
    }
}
