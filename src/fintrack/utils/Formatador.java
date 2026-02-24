package fintrack.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Formatador {
    private static final DateTimeFormatter FORMATTER_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATTER_ENTRADA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatarData(LocalDate data) {
        return data.format(FORMATTER_DATA);
    }

    public static LocalDate converterParaData(String dataString) throws DateTimeParseException {
        return LocalDate.parse(dataString, FORMATTER_ENTRADA);
    }

    public static String formatarValor(double valor) {
        return String.format("R$ %.2f", valor);
    }

    public static double converterParaDouble(String valor) throws NumberFormatException {
        return Double.parseDouble(valor.replace(",", "."));
    }

    public static String linha(int tamanho) {
        return "=".repeat(tamanho);
    }

    public static String criarCabecalho(String texto) {
        int tamanho = 50;
        int espacos = (tamanho - texto.length()) / 2;
        return linha(tamanho) + "\n" + " ".repeat(espacos) + texto +
               "\n" + linha(tamanho);
    }
}

