package fintrack.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe base para representar uma transação financeira
 */
public class Transacao {
    private static int proximoId = 1;
    private int id;
    private String descricao;
    private double valor;
    private LocalDate data;
    private TipoTransacao tipo;

    public enum TipoTransacao {
        RECEITA("Receita"),
        DESPESA("Despesa");

        private final String nome;

        TipoTransacao(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }
    }

    /**
     * Construtor padrão para criar uma transação
     */
    public Transacao(String descricao, double valor, LocalDate data, TipoTransacao tipo) {
        this.id = proximoId++;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor não pode ser negativo!");
        }
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("[ID: %d] %s - %s: R$ %.2f (Data: %s)",
                id, descricao, tipo.getNome(), valor, data.format(formatter));
    }
}

