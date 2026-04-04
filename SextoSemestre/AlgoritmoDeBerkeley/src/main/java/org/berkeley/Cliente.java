package org.berkeley;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

public class Cliente {
    private Random geradorDeTempo;
    private double relogioCliente;

    public Cliente() {
        this.geradorDeTempo = new Random();
        this.relogioCliente = geradorDeTempo.nextDouble(-25, +20);
    }

    public void iniciarCliente(String host, int porta) {
        try {
            Socket socket = new Socket(host, porta);
            System.out.println("RELOGIO DO CLIENTE ANTES DE INICIAR A THREAD: " + this.relogioCliente);
            ClienteThread clienteThread = new ClienteThread(socket, this);
            clienteThread.start();
            PrintStream envioDeDados = new PrintStream(socket.getOutputStream());
            envioDeDados.println(this.relogioCliente);
            System.out.println("RELOGIO DO CLIENTE DEPOIS DE ENVIAR O RELOGIO PRO SERVIDOR: " + this.relogioCliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setRelogioCliente(double novoHorario) {
        this.relogioCliente = novoHorario;
    }

    public double getRelogioCliente() {
        return relogioCliente;
    }
}
