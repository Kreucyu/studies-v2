package org.berkeley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int relogioServidor = 0;
        List<Integer> horariosRelogios = new ArrayList<>();
        try (ServerSocket serverSocket = new ServerSocket(4000)) {
            Socket socket = serverSocket.accept();
            System.out.println("O Cliente conectou");
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String horarioRelogio;
            while((horarioRelogio = bufferedReader.readLine()) != null) {
                horariosRelogios.add(Integer.parseInt(horarioRelogio));
                System.out.println("O HORARIO DO RELOGIO É" + horarioRelogio);
                System.out.println("O CALCULO DOS HORARIOS É: "  + calcularMedia(relogioServidor, horariosRelogios));
            }
            System.out.println("O HORARIO FINAL É: " + calcularMedia(relogioServidor, horariosRelogios));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static int calcularMedia(int relogioServidor, List<Integer> horariosRelogios) {
        int valorTotal = relogioServidor;
        for (Integer horario : horariosRelogios) {
            valorTotal += horario;
        }
        return valorTotal;
    }
}