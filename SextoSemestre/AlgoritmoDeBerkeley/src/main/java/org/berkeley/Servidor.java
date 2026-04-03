package org.berkeley;

import java.io.IOException;
import java.math.BigDecimal;
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

    public Servidor() {
        this.horariosRelogios = new ArrayList<>();
        this.clientesConectados = 0;
        this.quantidadeDeRespostas = 0;
        this.horarioServidor = 0.0;
        this.mediaAjuste = 0.0;
    }

    public void iniciarServidor(int porta) {
        try {
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
            this.horarioServidor += mediaAjuste;
            this.mediaAjuste = mediaAjuste;
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

    public double getMediaAjuste() {
        return this.mediaAjuste;
    }
}
