package org.berkeley;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

public class Cliente extends Thread {

    public static void main(String[] args) {
        Random geradorDeTempo = new Random();
        double relogioCliente = geradorDeTempo.nextDouble(-25, +20);

        try(Socket socket = new Socket("localhost", 4000)) {
            PrintStream envioDeDados = new PrintStream(socket.getOutputStream());
            envioDeDados.println(relogioCliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
