package repository;

import model.Jogo;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JogoRepository {
    public JogoRepository() {}

    public static void salvar(ArrayList<Jogo> jogos) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("jogosBacklog.csv"))) {
            writer.write("Id;Nome;Genero;Plataforma;Data");
            writer.newLine();
            for(Jogo j : jogos) {
                int id = j.getId();
                String nome = j.getNome();
                String genero = j.getGenero();
                String plataforma = j.getPlataforma();
                LocalDate data = j.getDataDeLancamento();
                writer.write(id + ";" + nome + ";" + genero + ";" + plataforma + ";" + data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Não foi possível salvar!\n");
        }
    }

    public static int carregarJogos(ArrayList<Jogo> jogos, int actualId, DateTimeFormatter dates) {
        try(BufferedReader reader = new BufferedReader(new FileReader("jogosBacklog.csv"))) {
            reader.readLine();
            String linha;
            while((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String genero = dados[2];
                String plataforma = dados[3];
                LocalDate data = LocalDate.parse(dados[4], dates);
                Jogo jogo = new Jogo(nome, data, plataforma, id, genero);
                jogos.add(jogo);
                actualId = id + 1;
            }
            return actualId;
        } catch (Exception e) {
            System.out.println("Não foi possível carregar os jogos!\n");
        }
        return 0;
    }
}
