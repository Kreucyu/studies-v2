package repository;

import model.Jogo;
import service.JogoRepository;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static service.JogoRepository.carregarJogos;
import static service.JogoRepository.salvar;

public class JogoService {
    public int actualId = 0;
    public final ArrayList<Jogo> jogos = new ArrayList<>();
    public final DateTimeFormatter dates = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final Scanner scanner = new Scanner(System.in);

    public JogoService() {
    }

    public void function() {
        actualId = carregarJogos(jogos, actualId, dates);
        int option;
        do {
            System.out.println("O que você deseja?\n1-Adicionar jogo\n2-Atualizar jogo\n3-Remover jogo\n4-Exibir lista de jogos\n5-Buscar jogo por nome\n6-Sair\n");
            option = Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch (option) {
                case 1:
                    adicionar();
                    break;
                case 2:
                    atualizar();
                    break;
                case 3:
                    remover();
                    break;
                case 4:
                    exibir();
                    break;
                case 5:
                    buscarNome();
                    break;
                case 6:
                    salvar(jogos);
                    scanner.close();
                    System.exit(0);
                    break;
            }
        } while (option != 6);
    }

    private void adicionar() {
        System.out.println("Qual o nome do seu jogo?");
        String nome = scanner.nextLine();
        System.out.println("Quais as plataformas do seu jogo?");
        String plataforma = scanner.nextLine();
        System.out.println("Quais os gêneros do seu jogo?");
        String genero = scanner.nextLine();
        System.out.println("Qual a data de lançamento do seu jogo (ano/mes/dia)?");
        String dataDeLancamento = scanner.nextLine();
        LocalDate date = LocalDate.parse(dataDeLancamento, dates);
        int id = actualId;
        Jogo jogo = new Jogo(nome, date, plataforma, id, genero);
        jogos.add(jogo);
        System.out.println("\nJogo adicionado com sucesso\n");
        actualId++;
        salvar(jogos);
    }

    private void atualizar() {
        System.out.println("Qual jogo você deseja atualizar? (selecione o ID)\n");
        exibir();
        int idSelecionado = Integer.parseInt(scanner.nextLine());
        for (Jogo j : jogos) {
            if (j.getId() == idSelecionado) {
                int alteracao;
                do {
                    System.out.println("\nO que deseja alterar?\n1-Nome\n2-Data de lançamento\n3-Plataforma\n4-Gênero\n5-Sair\n");
                    alteracao = Integer.parseInt(scanner.nextLine());
                    System.out.println();
                    switch (alteracao) {
                        case 1:
                            System.out.print("Digite o nome: ");
                            String novoNome = scanner.nextLine();
                            j.setNome(novoNome);
                            System.out.println("\nNome atualizado com sucesso");
                            break;
                        case 2:
                            System.out.print("Digite a data (ano/mes/dia): ");
                            String novaData = scanner.nextLine();
                            LocalDate date = LocalDate.parse(novaData, dates);
                            j.setDataDeLancamento(date);
                            System.out.println("\nData atualizada com sucesso");
                            break;
                        case 3:
                            System.out.print("Digite a(as) plataforma(as): ");
                            String novaPlataforma = scanner.nextLine();
                            j.setPlataforma(novaPlataforma);
                            System.out.println("\nPlataforma atualizada com sucesso");
                            break;
                        case 4:
                            System.out.print("Digite o(os) gênero(os): ");
                            String novoGenero = scanner.nextLine();
                            j.setGenero(novoGenero);
                            System.out.println("\nGênero atualizado com sucesso");
                            break;
                        case 5:
                            System.out.println("Voltando...\n");
                            salvar(jogos);
                            break;

                    }
                } while (alteracao != 5);
                return;
            }
         }
        System.out.println("Id não encontrado\n");
    }

        private void exibir() {
            if(jogos.isEmpty()) {
                System.out.println("Lista vazia!\n");
                return;
            }
            jogos.forEach(n -> System.out.println(n.toString() + "\n"));
        }

        private void remover() {
            System.out.println("Qual jogo você deseja deletar? (selecione o ID)\n");
            exibir();
            int idSelecionado = Integer.parseInt(scanner.nextLine());
            for (Jogo j : jogos) {
                if (j.getId() == idSelecionado) {
                    jogos.remove(j);
                    System.out.println("\nJogo removido com sucesso\n");
                    salvar(jogos);
                    return;
                }
            }
            System.out.println("Id não encontrado\n");
        }

        private void buscarNome() {
            System.out.println("Qual o nome do jogo que você procura?\n");
            String nomeBuscar = scanner.nextLine();
            System.out.println();
            boolean encontrou = false;
            for(Jogo j : jogos) {
                if(j.getNome().toLowerCase().contains(nomeBuscar.toLowerCase())) {
                    System.out.println(j + "\n");
                    encontrou = true;
                }
            }
            if(!encontrou) {
                System.out.println("Nenhum jogo foi encontrado!\n");
            }
        }
    }

