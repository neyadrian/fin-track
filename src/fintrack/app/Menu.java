package fintrack.app;

import fintrack.controller.FinTracker;
import fintrack.model.Transacao.TipoTransacao;
import fintrack.exceptions.EntradaInvalidaException;
import fintrack.utils.Formatador;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Classe principal da aplicação FinTrack com menu interativo
 */
public class Menu {
    private FinTracker finTracker;
    private Scanner scanner;
    private boolean rodando;

    public Menu() {
        this.finTracker = new FinTracker();
        this.scanner = new Scanner(System.in);
        this.rodando = true;
    }

    /**
     * Inicia a aplicação e o loop do menu
     */
    public void iniciar() {
        System.out.println("\n" + Formatador.criarCabecalho("FINTRACK - SEU CONTROLE FINANCEIRO"));
        System.out.println("\nBem-vindo ao FinTrack! \n");

        while (rodando) {
            exibirMenu();
            executarOpcao();
        }

        encerrar();
    }

    /**
     * Exibe o menu principal
     */
    private void exibirMenu() {
        System.out.println("\n" + Formatador.linha(40));
        System.out.println("1. Adicionar nova transação");
        System.out.println("2. Adicionar transação mensal");
        System.out.println("3. Listar transações");
        System.out.println("4. Mostrar saldo atual");
        System.out.println("5. Remover transação");
        System.out.println("6. Sair");
        System.out.println(Formatador.linha(40));
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Executa a opção selecionada pelo usuário
     */
    private void executarOpcao() {
        try {
            int opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    adicionarTransacao();
                    break;
                case 2:
                    adicionarTransacaoMensal();
                    break;
                case 3:
                    listarTransacoes();
                    break;
                case 4:
                    exibirSaldo();
                    break;
                case 5:
                    removerTransacao();
                    break;
                case 6:
                    rodando = false;
                    break;
                default:
                    System.out.println("\n❌ Opção inválida! Tente novamente.");
            }
        } catch (EntradaInvalidaException e) {
            System.out.println("\n❌ Erro: " + e.getMessage());
        }
    }

    /**
     * Adiciona uma nova transação simples
     */
    private void adicionarTransacao() throws EntradaInvalidaException {
        System.out.println("\n" + Formatador.linha(40));
        System.out.println("ADICIONAR NOVA TRANSAÇÃO");
        System.out.println(Formatador.linha(40));

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine().trim();

        if (descricao.isEmpty()) {
            throw new EntradaInvalidaException("Descrição não pode estar vazia!");
        }

        System.out.print("Valor (use . ou , para decimais): ");
        double valor;
        try {
            valor = Formatador.converterParaDouble(scanner.nextLine().trim());
            if (valor <= 0) {
                throw new EntradaInvalidaException("O valor deve ser maior que zero!");
            }
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Valor inválido! Use números com . ou , para decimais.");
        }

        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate data;
        try {
            data = Formatador.converterParaData(scanner.nextLine().trim());
        } catch (DateTimeParseException e) {
            throw new EntradaInvalidaException("Data inválida! Use o formato dd/MM/yyyy.");
        }

        System.out.println("\nTipo de transação:");
        System.out.println("1. Receita");
        System.out.println("2. Despesa");
        System.out.print("Escolha: ");
        int tipo = lerInteiro();

        TipoTransacao tipoTransacao;
        if (tipo == 1) {
            tipoTransacao = TipoTransacao.RECEITA;
        } else if (tipo == 2) {
            tipoTransacao = TipoTransacao.DESPESA;
        } else {
            throw new EntradaInvalidaException("Tipo de transação inválido!");
        }

        try {
            finTracker.adicionarTransacao(descricao, valor, data, tipoTransacao);
            System.out.println("\n✓ Transação adicionada com sucesso!");
        } catch (IllegalArgumentException e) {
            throw new EntradaInvalidaException(e.getMessage());
        }
    }

    /**
     * Adiciona uma transação mensal recorrente
     */
    private void adicionarTransacaoMensal() throws EntradaInvalidaException {
        System.out.println("\n" + Formatador.linha(40));
        System.out.println("ADICIONAR TRANSAÇÃO MENSAL (RECORRENTE)");
        System.out.println(Formatador.linha(40));

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine().trim();

        if (descricao.isEmpty()) {
            throw new EntradaInvalidaException("Descrição não pode estar vazia!");
        }

        System.out.print("Valor (use . ou , para decimais): ");
        double valor;
        try {
            valor = Formatador.converterParaDouble(scanner.nextLine().trim());
            if (valor <= 0) {
                throw new EntradaInvalidaException("O valor deve ser maior que zero!");
            }
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Valor inválido! Use números com . ou , para decimais.");
        }

        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate data;
        try {
            data = Formatador.converterParaData(scanner.nextLine().trim());
        } catch (DateTimeParseException e) {
            throw new EntradaInvalidaException("Data inválida! Use o formato dd/MM/yyyy.");
        }

        System.out.println("\nTipo de transação:");
        System.out.println("1. Receita");
        System.out.println("2. Despesa");
        System.out.print("Escolha: ");
        int tipo = lerInteiro();

        TipoTransacao tipoTransacao;
        if (tipo == 1) {
            tipoTransacao = TipoTransacao.RECEITA;
        } else if (tipo == 2) {
            tipoTransacao = TipoTransacao.DESPESA;
        } else {
            throw new EntradaInvalidaException("Tipo de transação inválido!");
        }

        System.out.print("Mês da recorrência (1-12): ");
        int mes;
        try {
            mes = lerInteiro();
            if (mes < 1 || mes > 12) {
                throw new EntradaInvalidaException("Mês deve estar entre 1 e 12!");
            }
        } catch (EntradaInvalidaException e) {
            throw e;
        }

        try {
            finTracker.adicionarTransacaoMensal(descricao, valor, data, tipoTransacao, mes);
            System.out.println("\n✓ Transação mensal adicionada com sucesso!");
        } catch (IllegalArgumentException e) {
            throw new EntradaInvalidaException(e.getMessage());
        }
    }

    /**
     * Lista todas as transações cadastradas
     */
    private void listarTransacoes() {
        System.out.println("\n" + Formatador.linha(50));
        System.out.println("LISTA DE TRANSAÇÕES");
        System.out.println(Formatador.linha(50));

        if (finTracker.getTotalTransacoes() == 0) {
            System.out.println("\n📋 Nenhuma transação cadastrada.\n");
            return;
        }

        System.out.println();
        for (var transacao : finTracker.listarTransacoes()) {
            System.out.println(transacao);
        }
        System.out.println();
    }

    /**
     * Exibe o saldo atual com detalhamento
     */
    private void exibirSaldo() {
        System.out.println("\n" + Formatador.linha(50));
        System.out.println("SALDO ATUAL");
        System.out.println(Formatador.linha(50));

        double receitas = finTracker.calcularTotalReceitas();
        double despesas = finTracker.calcularTotalDespesas();
        double saldo = finTracker.calcularSaldoTotal();

        System.out.println("\n💰 Total de Receitas: " + Formatador.formatarValor(receitas));
        System.out.println("💸 Total de Despesas: " + Formatador.formatarValor(despesas));
        System.out.println(Formatador.linha(50));

        if (saldo >= 0) {
            System.out.println("✓ Saldo: " + Formatador.formatarValor(saldo) + " (Positivo)");
        } else {
            System.out.println("✗ Saldo: " + Formatador.formatarValor(saldo) + " (Negativo)");
        }
        System.out.println();
    }

    /**
     * Remove uma transação pelo ID
     */
    private void removerTransacao() throws EntradaInvalidaException {
        if (finTracker.getTotalTransacoes() == 0) {
            System.out.println("\n📋 Nenhuma transação cadastrada para remover.\n");
            return;
        }

        System.out.println("\n" + Formatador.linha(50));
        System.out.println("REMOVER TRANSAÇÃO");
        System.out.println(Formatador.linha(50));

        listarTransacoes();

        System.out.print("Digite o ID da transação a remover: ");
        int id = lerInteiro();

        if (finTracker.removerTransacao(id)) {
            System.out.println("\n✓ Transação removida com sucesso!");
        } else {
            System.out.println("\n❌ Transação com ID " + id + " não encontrada!");
        }
    }

    /**
     * Método auxiliar para ler um inteiro com tratamento de erro
     */
    private int lerInteiro() throws EntradaInvalidaException {
        try {
            String entrada = scanner.nextLine().trim();
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Entrada inválida! Esperado um número inteiro.");
        }
    }

    /**
     * Encerra a aplicação
     */
    private void encerrar() {
        scanner.close();
        System.out.println("\n" + Formatador.criarCabecalho("OBRIGADO POR USAR FINTRACK!"));
        System.out.println("\nAté logo! 👋\n");
    }
}

