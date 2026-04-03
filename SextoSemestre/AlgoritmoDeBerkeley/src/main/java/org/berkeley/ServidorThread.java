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

        synchronized (this) {
            servidor.adicionarCliente();
        }

        System.out.println("O cliente conectou!");

        try {

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String horarioRelogio = bufferedReader.readLine();
            int horarioRelogioConverido = Integer.parseInt(horarioRelogio);

            synchronized (this) {
                servidor.adicionarHorario(horarioRelogioConverido);
                servidor.respostasCliente();
                servidor.iniciarSincronizacao();
            }

            double mediaAjuste = servidor.getMediaAjuste();
            if (mediaAjuste != 0.0) {
                double novoHorario = mediaAjuste - horarioRelogioConverido;
                PrintStream printStream = new PrintStream(socket.getOutputStream());
                printStream.println(novoHorario);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
