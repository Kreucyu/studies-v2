package com.elcio;

import com.elcio.repository.DatabaseManager;
import com.elcio.service.JogoService;

public class App {
    public static void main(String[] args) {
        System.out.println("CRUD simples:\n");
        DatabaseManager manager = new DatabaseManager();
        manager.inicializarBanco();
        JogoService service = new JogoService();
        service.function();
    }
}