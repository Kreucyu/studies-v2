package org.berkeley;

public class MainCliente {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.iniciarCliente("192.168.1.19", 4000);
    }
}
