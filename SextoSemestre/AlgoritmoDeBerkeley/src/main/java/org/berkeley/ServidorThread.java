package org.berkeley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServidorThread extends Thread {
    private Socket socket;
    private Servidor servidor;

    public ServidorThread(Socket socket) {
        this.socket = socket;
        this.servidor = new Servidor();
    }
    @Override
    public void run() {
        servidor.adicionarCliente();
        System.out.println("O cliente conectou!");
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String horarioRelogio = bufferedReader.readLine();
            servidor.adicionarHorario(Integer.parseInt(horarioRelogio));
            servidor.respostasCliente();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
