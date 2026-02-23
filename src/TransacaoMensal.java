import java.time.LocalDate;

public class TransacaoMensal extends Transacao {
    private int mesesRecorrencia;

    public TransacaoMensal(String descricao, double valor, LocalDate data, String tipo, int mesesRecorrencia) {
        super(descricao, valor, data, tipo);

        if(mesesRecorrencia <= 0) {
            throw new IllegalArgumentException("Meses de recorrência deve ser maior que zero");
        }

        this.mesesRecorrencia = mesesRecorrencia;
    }

    public int getMesesRecorrencia() {
        return mesesRecorrencia;
    }

    public void setMesesRecorrencia(int mesesRecorrencia) {

        if(mesesRecorrencia <= 0) {
            throw new IllegalArgumentException("Meses de recorrência deve ser maior que zero");
        }
        this.mesesRecorrencia = mesesRecorrencia;
    }
}
