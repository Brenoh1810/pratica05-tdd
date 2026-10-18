package com.gqs.aula.tdd;

public class CalculadoraNota {

    public static final double NOTA_MINIMA_APROVACAO = 70;
    public static final double NOTA_MAXIMA_A1_A2 = 30;
    public static final double NOTA_MAXIMA_A3 = 40;
    public static final double FREQUENCIA_MINIMA = 75;
    public static final double SOMA_MINIMA_AI = 40;

    public double calcularNotaFinal(double a1, double a2, double a3) {
        validarNota(a1, NOTA_MAXIMA_A1_A2, "A1");
        validarNota(a2, NOTA_MAXIMA_A1_A2, "A2");
        validarNota(a3, NOTA_MAXIMA_A3, "A3");
        return a1 + a2 + a3;
    }

    public boolean estaAprovadoPorNota(double notaFinal) {
        return notaFinal >= NOTA_MINIMA_APROVACAO;
    }

    private void validarNota(double nota, double notaMaxima, String nomeAvaliacao) {
        if (nota < 0 || nota > notaMaxima) {
            throw new IllegalArgumentException(
                nomeAvaliacao + " deve estar entre 0 e " + notaMaxima);
        }
    }

    public String situacao(double notaFinal, double frequencia) {
        if (estaAprovadoPorNota(notaFinal) && frequencia >= FREQUENCIA_MINIMA) {
            return "APROVADO";
        }
        return "REPROVADO";
    }

    public boolean podeFazerAI(double a1, double a2, double a3, double frequencia) {
        double notaFinal = calcularNotaFinal(a1, a2, a3);
        return !estaAprovadoPorNota(notaFinal)
            && frequencia >= FREQUENCIA_MINIMA
            && atingiuSomaMinimaParaAI(a1, a2, a3);
    }

    private boolean atingiuSomaMinimaParaAI(double a1, double a2, double a3) {
        return a1 + a3 >= SOMA_MINIMA_AI || a2 + a3 >= SOMA_MINIMA_AI;
    }

    public double aplicarAI(double a1, double a2, double a3, double notaAI) {
        validarNota(notaAI, 30, "AI");
        if (notaAI <= Math.min(a1, a2)) {
            return calcularNotaFinal(a1, a2, a3);
        }
        if (a1 <= a2) {
            return calcularNotaFinal(notaAI, a2, a3);
        }
        return calcularNotaFinal(a1, notaAI, a3);
    }
}
