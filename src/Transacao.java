import java.time.LocalDate;

public class Transacao {

    private String descricao;
    private double valor;
    private LocalDate data;
    private String tipo;

    public Transacao(String descricao, double valor, LocalDate data, String tipo) {

        if(valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero!");
        }

        if (!tipo.equalsIgnoreCase("RECEITA") && !tipo.equalsIgnoreCase("DISPESA")) {
            throw new IllegalArgumentException("Tipo deve ser RECEITA ou DESPESA!");
        }

        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo.toUpperCase();
    }
}
