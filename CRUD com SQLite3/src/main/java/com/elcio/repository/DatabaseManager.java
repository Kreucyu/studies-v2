package com.elcio.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DatabaseManager {

    public DatabaseManager() {}

    public void inicializarBanco() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:jogos.db");
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
