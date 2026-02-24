import java.util.ArrayList;
import java.util.List;

public class FinTracker {
    private List<Transacao> transacoes;

    public FinTracker(List<Transacao> transacoes) {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao transacao) {
        if(transacao == null) {
            throw new IllegalArgumentException("Transação não pode ser nula");
        }
        transacoes.add(transacao);
        System.out.println("Transação adicionada com sucesso!");
    }

    public void listarTransacoes() {
        if(transacoes.isEmpty()) {
            System.out.println("\n Nenhuma transação registrada.");
            return;
        }

        System.out.println("\n===== LISTA DE TRANSAÇÕES =====");
        for(int i = 0; i < transacoes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, transacoes.get(i));
        }
        System.out.println("=====================================\n");
    }

    public void removerTransacao(int indice) {
        if (indice < 0 || indice >= transacoes.size()) {
            System.out.println("Índice inválido!");
            return;
        }

        Transacao removida = transacoes.remove(indice);
        System.out.println("Transação removida: " + removida);
    }

    public double calcularSaldoTotal() {
        double saldo = 0;

        for (Transacao transacao : transacoes) {
            if (transacao.getTipo().equals("RECEITA")) {
                saldo += transacao.getValor();
            } else if (transacao.getTipo().equals("DESPESA")) {
                saldo -= transacao.getValor();
            }
        }

        return saldo;
    }

    public void exibirSaldoAtual() {
        double saldo = calcularSaldoTotal();
        System.out.println("\n===== SALDO ATUAL =====");
        System.out.printf("R$ %.2f%n", saldo);

        if (saldo > 0) {
            System.out.println("Você está no positivo!");
        } else if (saldo < 0) {
            System.out.println("Você está no negativo!");
        } else {
            System.out.println("Seu saldo está zerado.");
        }
        System.out.println("=======================\n");
    }

    public int getTotalTransacoes() {
        return transacoes.size();
    }

    public List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes);
    }
}
