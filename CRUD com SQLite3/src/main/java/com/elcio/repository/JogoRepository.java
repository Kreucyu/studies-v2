package com.elcio.repository;
import com.elcio.model.Jogo;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JogoRepository {
    public static final DateTimeFormatter dates = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DatabaseManager acess = new DatabaseManager();
    public JogoRepository() {}

    public static void create(Jogo jogo) {
        try (Connection connection = acess.getConnection();) {
            String commandSql = "INSERT INTO jogos (nome, dataLancamento, plataforma, genero) VALUES (?,?,?,?)";
            PreparedStatement creator = connection.prepareStatement(commandSql);
            creator.setString(1, jogo.getNome());
            creator.setString(2, jogo.getDataDeLancamento().toString());
            creator.setString(3, jogo.getPlataforma());
            creator.setString(4, jogo.getGenero());
            int resultado = creator.executeUpdate();
            if (resultado == 1) {System.out.println("\nJogo adicionado com sucesso!\n");}
            creator.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void read() {
        try(Connection connection = acess.getConnection();) {
            String commandSql = "SELECT * FROM jogos";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(commandSql);
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("nome");
                String data = resultSet.getString("dataLancamento");
                LocalDate dataLancamento = LocalDate.parse(data, dates);
                String plataforma = resultSet.getString("plataforma");
                String genero = resultSet.getString("genero");
                Jogo jogo = new Jogo(nome, dataLancamento, plataforma, genero);
                jogo.setId(id);
                System.out.println(jogo.toString()+"\n");
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(int idUpdate, String novoDado, int tipo) {

            String commandSql = null;
            switch (tipo) {
                case 1:
                    commandSql = "UPDATE jogos SET nome = ? WHERE ID= ?";
                break;
                case 2:
                    commandSql = "UPDATE jogos SET dataLancamento = ? WHERE ID= ?";
                break;
                case 3:
                    commandSql = "UPDATE jogos SET plataforma = ? WHERE ID= ?";
                break;
                case 4:
                    commandSql = "UPDATE jogos SET genero = ? WHERE ID= ?";
                break;
            }

        try(Connection connection = acess.getConnection();) {
            PreparedStatement creator = connection.prepareStatement(commandSql);
            creator.setString(1, novoDado);
            creator.setInt(2, idUpdate);
            int resultado = creator.executeUpdate();
            if (resultado == 1) {System.out.println("\nJogo atualizado com sucesso!");}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(int idDelete) {
        try (Connection connection = acess.getConnection();) {
            String commandSql = "DELETE FROM jogos WHERE ID= ?";
            PreparedStatement creator = connection.prepareStatement(commandSql);
            creator.setInt(1, idDelete);
            int resultado = creator.executeUpdate();
            if (resultado == 1) {System.out.println("\nJogo deletado com sucesso!\n");}
            creator.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void searchWith(String busca, int tipo) {
        String commandSql = null;
        switch(tipo) {
            case 1:
                commandSql = "SELECT * FROM jogos WHERE nome LIKE ? COLLATE NOCASE";
                break;
            case 2:
                commandSql = "SELECT * FROM jogos WHERE plataforma LIKE ? COLLATE NOCASE";
                break;
            case 3:
                commandSql = "SELECT * FROM jogos WHERE genero LIKE ? COLLATE NOCASE";
                break;
            case 4:
                commandSql = "SELECT * FROM jogos WHERE dataLancamento LIKE ? COLLATE NOCASE";
                break;
        }
        try(Connection connection = acess.getConnection()) {
            if(commandSql != null) {
                PreparedStatement creator = connection.prepareStatement(commandSql);
                creator.setString(1, "%" + busca + "%");
                ResultSet resultSet = creator.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String nome = resultSet.getString("nome");
                    String plataforma = resultSet.getString("plataforma");
                    String genero = resultSet.getString("genero");
                    String dataLancamento = resultSet.getString("dataLancamento");
                    LocalDate data = LocalDate.parse(dataLancamento, dates);
                    Jogo jogo = new Jogo(nome, data, plataforma, genero);
                    jogo.setId(id);
                    System.out.println(jogo.toString() + "\n");
                }
            } else {
                System.out.println("\nBusca inválida!");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
