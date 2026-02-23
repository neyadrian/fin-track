package fintrack.model;

import java.time.LocalDate;

/**
 * Classe que representa uma transação mensal recorrente (ex: salário fixo, aluguel)
 */
public class TransacaoMensal extends Transacao {
    private int mesRecorrencia;
    private boolean ativa;

    /**
     * Construtor para criar uma transação mensal recorrente
     */
    public TransacaoMensal(String descricao, double valor, LocalDate data,
                          TipoTransacao tipo, int mesRecorrencia) {
        super(descricao, valor, data, tipo);
        this.mesRecorrencia = mesRecorrencia;
        this.ativa = true;
    }

    public int getMesRecorrencia() {
        return mesRecorrencia;
    }

    public void setMesRecorrencia(int mesRecorrencia) {
        if (mesRecorrencia < 1 || mesRecorrencia > 12) {
            throw new IllegalArgumentException("Mês deve estar entre 1 e 12!");
        }
        this.mesRecorrencia = mesRecorrencia;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" [RECORRENTE - Mês: %d, Ativa: %s]", mesRecorrencia, ativa);
    }
}

