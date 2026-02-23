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

}
