package org.berkeley;

import java.net.Socket;

public class ClienteThread extends Thread {
    private Socket clienteSocket;

    public ClienteThread(Socket socket) {
        this.clienteSocket = socket;
    }
    @Override
    public void run() {
        System.out.println("Conectado!");
    }
}
