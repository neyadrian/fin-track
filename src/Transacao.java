import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transacao {

    private String descricao;
    private double valor;
    private LocalDate data;
    private String tipo;

    public Transacao(String descricao, double valor, LocalDate data, String tipo) {

        if(valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }

        if (!tipo.equalsIgnoreCase("RECEITA") && !tipo.equalsIgnoreCase("DESPESA")) {
            throw new IllegalArgumentException("Tipo deve ser RECEITA ou DESPESA");
        }

        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo.toUpperCase();
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
        this.valor = valor;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("[%s] %s - R$ %.2f (%s)",
                data.format(formatter), descricao, valor, tipo);
    }
}
