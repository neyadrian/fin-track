package fintrack.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Classe utilitária para formatação de datas, valores e outras conversões
 */
public class Formatador {
    private static final DateTimeFormatter FORMATTER_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATTER_ENTRADA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Formata uma data para o padrão dd/MM/yyyy
     */
    public static String formatarData(LocalDate data) {
        return data.format(FORMATTER_DATA);
    }

    /**
     * Converte uma String para LocalDate no padrão dd/MM/yyyy
     */
    public static LocalDate converterParaData(String dataString) throws DateTimeParseException {
        return LocalDate.parse(dataString, FORMATTER_ENTRADA);
    }

    /**
     * Formata um valor monetário para R$ X.XX
     */
    public static String formatarValor(double valor) {
        return String.format("R$ %.2f", valor);
    }

    /**
     * Converte uma String para double, tratando exceções
     */
    public static double converterParaDouble(String valor) throws NumberFormatException {
        return Double.parseDouble(valor.replace(",", "."));
    }

    /**
     * Cria uma linha separadora visual
     */
    public static String linha(int tamanho) {
        return "=".repeat(tamanho);
    }

    /**
     * Cria um cabeçalho centralizado
     */
    public static String criarCabecalho(String texto) {
        int tamanho = 50;
        int espacos = (tamanho - texto.length()) / 2;
        return linha(tamanho) + "\n" + " ".repeat(espacos) + texto +
               "\n" + linha(tamanho);
    }
}

