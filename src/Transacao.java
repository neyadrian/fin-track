import java.time.LocalDate;

public class Transacao {

    private String descricao;
    private double valor;
    private LocalDate data;
    private String tipo;

    public Transacao(String descricao, double valor, LocalDate data, String tipo) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
    }
}
