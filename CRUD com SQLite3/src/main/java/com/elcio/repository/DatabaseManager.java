package com.elcio.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    public DatabaseManager() {}

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:jogos.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void inicializarBanco() {
        try {
            Connection connection = getConnection();
            String sqlCommand =
                    "CREATE TABLE IF NOT EXISTS jogos (" +
                            "id INTEGER PRIMARY KEY NOT NULL, " +
                            "nome TEXT NOT NULL, " +
                            "dataLancamento TEXT NOT NULL, " +
                            "plataforma TEXT NOT NULL, " +
                            "genero TEXT NOT NULL" +
                            ");";

            Statement statement = connection.createStatement();
            statement.execute(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
