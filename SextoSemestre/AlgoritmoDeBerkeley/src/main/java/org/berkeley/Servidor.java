package org.berkeley;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private double horarioServidor;
    private ServerSocket serverSocket;
    private List<Double> horariosRelogios;
    private int clientesConectados;
    private int quantidadeDeRespostas;
    private double mediaAjuste;
    private boolean podeSincronizar;

    public Servidor() {
        this.horariosRelogios = new ArrayList<>();
        this.clientesConectados = 0;
        this.quantidadeDeRespostas = 0;
        this.horarioServidor = 0.0;
        this.mediaAjuste = 0.0;
        this.podeSincronizar = false;
    }

    public void iniciarServidor(int porta) {
        try {
            System.out.println("RELOGIO DO SERVIDOR ANTES DA SINCRONIZACAO: " + horarioServidor);
            serverSocket = new ServerSocket(porta);
            horariosRelogios.add(horarioServidor);
            while(true) {
                new ServidorThread(serverSocket.accept(), this).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void iniciarSincronizacao() {
        if(quantidadeDeRespostas == clientesConectados && quantidadeDeRespostas != 0) {
            double mediaAjuste = calcularMedia();
            this.horarioServidor = mediaAjuste - horarioServidor;
            this.mediaAjuste = mediaAjuste;
            this.podeSincronizar = true;
            notifyAll();
            System.out.println("RELOGIO DO SERVIDOR DEPOIS DA SINCRONIZACAO: " + horarioServidor);
        }
    }

    private double calcularMedia() {
        return horariosRelogios.stream().mapToDouble(h -> h).average().getAsDouble();
    }

    public void adicionarHorario(double horario) {
        horariosRelogios.add(horario);
    }

    public void respostasCliente() {this.quantidadeDeRespostas++;}

    public void reiniciarRespostas() {this.quantidadeDeRespostas = 0;}

    public void adicionarCliente() {this.clientesConectados++;}

    public void removerCliente() {this.clientesConectados--;}

    public int getClientesConectados() {
        return this.clientesConectados;
    }

    public int getQuantidadeDeRespostas() {
        return this.quantidadeDeRespostas;
    }

    public double getMediaAjuste() {
        return this.mediaAjuste;
    }

    public boolean estaProntoParaSincronizar() {
        return this.podeSincronizar;
    }
}
