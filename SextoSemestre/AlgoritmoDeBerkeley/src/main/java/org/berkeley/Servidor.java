package org.berkeley;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private ServerSocket serverSocket;
    private List<Integer> horariosRelogios;
    private int clientesConectados;
    private int quantidadeDeRespostas;

    public Servidor() {
        this.horariosRelogios = new ArrayList<>();
        this.clientesConectados = 0;
        this.quantidadeDeRespostas = 0;
    }

    public void iniciarServidor(int porta) {
        try {
            serverSocket = new ServerSocket(porta);
            while(true) {
                new ServidorThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarHorario(int horario) {
        horariosRelogios.add(horario);
    }

    public void respostasCliente() {this.quantidadeDeRespostas++;}

    public void reiniciarRespostas() {this.quantidadeDeRespostas = 0;}

    public void adicionarCliente() {this.clientesConectados++;}

    public void removerCliente() {this.clientesConectados--;}
}
