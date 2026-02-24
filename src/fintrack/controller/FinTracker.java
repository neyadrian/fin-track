package fintrack.controller;

import fintrack.model.Transacao;
import fintrack.model.Transacao.TipoTransacao;
import fintrack.model.TransacaoMensal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinTracker {
    private List<Transacao> transacoes;

    public FinTracker() {
        this.transacoes = new ArrayList<>();
    }

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

    public List<Transacao> listarTransacoes() {
        return new ArrayList<>(transacoes);
    }

    public int getTotalTransacoes() {
        return transacoes.size();
    }

    public boolean removerTransacao(int id) {
        return transacoes.removeIf(t -> t.getId() == id);
    }

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

    public double calcularTotalReceitas() {
        return transacoes.stream()
                .filter(t -> t.getTipo() == TipoTransacao.RECEITA)
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    public double calcularTotalDespesas() {
        return transacoes.stream()
                .filter(t -> t.getTipo() == TipoTransacao.DESPESA)
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    public Transacao obterTransacaoPorId(int id) {
        return transacoes.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void limparTransacoes() {
        transacoes.clear();
    }
}

