package com.elcio.service;
import com.elcio.model.Jogo;
import com.elcio.repository.JogoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class JogoService {
    public final DateTimeFormatter dates = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final Scanner scanner = new Scanner(System.in);

    public JogoService() {
    }

    public void function() {
        int option;
        do {
            System.out.println("O que você deseja?\n1-Adicionar jogo\n2-Atualizar jogo\n3-Remover jogo\n4-Exibir lista de jogos\n5-Buscar com filtro\n6-Sair\n");
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
                    JogoRepository.read();
                    break;
                case 5:
                    buscar();
                    break;
                case 6:
                    scanner.close();
                    System.exit(0);
                    break;
            }
        } while (option != 6);
    }

    private void adicionar() {
        System.out.println("Qual o nome do seu jogo?");
        String nome = scanner.nextLine();
        if(nome.equals("")) {
            throw new IllegalArgumentException("Nome Inválido");
        }
        System.out.println("Quais as plataformas do seu jogo?");
        String plataforma = scanner.nextLine();
        if(plataforma.equals("")) {
            throw new IllegalArgumentException("Plataforma Inválida");
        }
        System.out.println("Quais os gêneros do seu jogo?");
        String genero = scanner.nextLine();
        if(genero.equals("")) {
            throw new IllegalArgumentException("Gênero Inválido");
        }
        System.out.println("Qual a data de lançamento do seu jogo (ano/mes/dia)?");
        String dataDeLancamento = scanner.nextLine();
        if(dataDeLancamento.equals("")) {
            throw new IllegalArgumentException("Data Inválida");
        }
        LocalDate date = LocalDate.parse(dataDeLancamento, dates);
        Jogo jogo = new Jogo(nome, date, plataforma, genero);
        JogoRepository.create(jogo);
    }

    private void atualizar() {
        System.out.println("Qual jogo você deseja atualizar? (selecione o ID)\n");
        JogoRepository.read();
        int idEscolhido = Integer.parseInt(scanner.nextLine());
                int alteracao;
                do {
                    System.out.println("\nO que deseja alterar?\n1-Nome\n2-Data de lançamento\n3-Plataforma\n4-Gênero\n5-Sair\n");
                    alteracao = Integer.parseInt(scanner.nextLine());
                    System.out.println();
                    switch (alteracao) {
                        case 1:
                            System.out.print("Digite o nome: ");
                            String novoNome = scanner.nextLine();
                            JogoRepository.update(idEscolhido, novoNome, 1);
                            break;
                        case 2:
                            System.out.print("Digite a data (ano/mes/dia): ");
                            String novaData = scanner.nextLine();
                            JogoRepository.update(idEscolhido, novaData, 2);
                            break;
                        case 3:
                            System.out.print("Digite a(as) plataforma(as): ");
                            String novaPlataforma = scanner.nextLine();
                            JogoRepository.update(idEscolhido, novaPlataforma, 3);
                            break;
                        case 4:
                            System.out.print("Digite o(os) gênero(os): ");
                            String novoGenero = scanner.nextLine();
                            JogoRepository.update(idEscolhido, novoGenero, 4);
                            break;
                        case 5:
                            System.out.println("Voltando...\n");
                            break;
                    }
                } while (alteracao != 5);
    }

    private void remover() {
        System.out.println("Qual jogo você deseja deletar? (selecione o ID)\n");
        JogoRepository.read();
        int idEscolhido = Integer.parseInt(scanner.nextLine());
        JogoRepository.delete(idEscolhido);
    }

    private void buscar() {
        int option = 0;
        do {
            System.out.println("Deseja buscar por:\n1-Nome\n2-Plataforma\n3-Gênero\n4-Data de Lançamento\n5-Cancelar\n");
            option = Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch(option) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.println();
                    JogoRepository.searchWith(nome, 1);
                    break;
                case 2:
                    System.out.print("Digite a plataforma: ");
                    String plataforma = scanner.nextLine();
                    System.out.println();
                    JogoRepository.searchWith(plataforma, 2);
                    break;
                case 3:
                    System.out.print("Digite o gênero: ");
                    String genero = scanner.nextLine();
                    System.out.println();
                    JogoRepository.searchWith(genero, 3);
                    break;
                case 4:
                    System.out.print("Digite a data: ");
                    String data = scanner.nextLine();
                    System.out.println();
                    JogoRepository.searchWith(data, 4);
                    break;
                case 5:
                    System.out.println("Voltando...\n");
                    break;
            }

        } while(option != 5);
    }
}



