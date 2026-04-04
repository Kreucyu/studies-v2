package org.berkeley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServidorThread extends Thread {
    private Socket socket;
    private Servidor servidor;

    public ServidorThread(Socket socket, Servidor servidor) {
        this.socket = socket;
        this.servidor = servidor;
    }
    @Override
    public void run() {
        try {
            synchronized (servidor) {
                servidor.adicionarCliente();
            }
            System.out.println("O cliente conectou!");
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String horarioRelogio = bufferedReader.readLine();
            double horarioRelogioConverido = Double.parseDouble(horarioRelogio);

            synchronized (servidor) {
                servidor.adicionarHorario(horarioRelogioConverido);
                servidor.respostasCliente();
                if(servidor.getClientesConectados() == servidor.getQuantidadeDeRespostas()) {
                    servidor.iniciarSincronizacao();
                }
                while(!servidor.estaProntoParaSincronizar()) {
                    servidor.wait();
                }

            }
            double mediaAjuste = servidor.getMediaAjuste();
            double novoHorario = mediaAjuste + (-1 * horarioRelogioConverido);
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            printStream.println(novoHorario);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
