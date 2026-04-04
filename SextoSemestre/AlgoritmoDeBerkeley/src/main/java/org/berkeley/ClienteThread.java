package org.berkeley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienteThread extends Thread {
    private Socket clienteSocket;
    private Cliente cliente;

    public ClienteThread(Socket socket, Cliente cliente) {
        this.clienteSocket = socket;
        this.cliente = cliente;
    }
    @Override
    public void run() {
        try {
            System.out.println("RELOGIO DO CLIENTE NO COMECO DA THREAD: " + cliente.getRelogioCliente());
            InputStreamReader inputStreamReader = new InputStreamReader(clienteSocket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String horarioRelogio = bufferedReader.readLine();
            double horarioAtualizado = Double.parseDouble(horarioRelogio);
            cliente.setRelogioCliente(horarioAtualizado);
            System.out.println("RELOGIO DO CLIENTE NO FINAL DA THREAD: " + cliente.getRelogioCliente());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
