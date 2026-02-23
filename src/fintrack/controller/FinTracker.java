package fintrack.controller;

import fintrack.model.Transacao;
import fintrack.model.Transacao.TipoTransacao;
import fintrack.model.TransacaoMensal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe controladora que gerencia as operações com transações
 */
public class FinTracker {
    private List<Transacao> transacoes;

    public FinTracker() {
        this.transacoes = new ArrayList<>();
    }

    /**
     * Adiciona uma nova transação simples
     */
    public void adicionarTransacao(String descricao, double valor, LocalDate data, TipoTransacao tipo) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode estar vazia!");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero!");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula!");
        }

        Transacao transacao = new Transacao(descricao, valor, data, tipo);
        transacoes.add(transacao);
    }

    /**
     * Adiciona uma transação mensal recorrente
     */
    public void adicionarTransacaoMensal(String descricao, double valor, LocalDate data,
                                        TipoTransacao tipo, int mesRecorrencia) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode estar vazia!");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero!");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula!");
        }
        if (mesRecorrencia < 1 || mesRecorrencia > 12) {
            throw new IllegalArgumentException("Mês deve estar entre 1 e 12!");
        }

        TransacaoMensal transacao = new TransacaoMensal(descricao, valor, data, tipo, mesRecorrencia);
        transacoes.add(transacao);
    }

    /**
     * Lista todas as transações cadastradas
     */
    public List<Transacao> listarTransacoes() {
        return new ArrayList<>(transacoes);
    }

    /**
     * Retorna o número total de transações
     */
    public int getTotalTransacoes() {
        return transacoes.size();
    }

    /**
     * Remove uma transação pelo ID
     */
    public boolean removerTransacao(int id) {
        return transacoes.removeIf(t -> t.getId() == id);
    }

    /**
     * Calcula o saldo total (receitas - despesas)
     */
    public double calcularSaldoTotal() {
        double saldo = 0;
        for (Transacao t : transacoes) {
            if (t.getTipo() == TipoTransacao.RECEITA) {
                saldo += t.getValor();
            } else {
                saldo -= t.getValor();
            }
        }
        return saldo;
    }

    /**
     * Calcula o total de receitas
     */
    public double calcularTotalReceitas() {
        return transacoes.stream()
                .filter(t -> t.getTipo() == TipoTransacao.RECEITA)
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    /**
     * Calcula o total de despesas
     */
    public double calcularTotalDespesas() {
        return transacoes.stream()
                .filter(t -> t.getTipo() == TipoTransacao.DESPESA)
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    /**
     * Obtém uma transação pelo ID
     */
    public Transacao obterTransacaoPorId(int id) {
        return transacoes.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Limpa todas as transações (cuidado com este método!)
     */
    public void limparTransacoes() {
        transacoes.clear();
    }
}

