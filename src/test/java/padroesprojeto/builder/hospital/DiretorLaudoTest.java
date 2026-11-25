package padroesprojeto.builder.hospital;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiretorLaudoTest {

    DiretorLaudo diretor = new DiretorLaudo();

    @Test
    void deveMontarLaudoCompletoEmTexto() {
        Laudo laudo = diretor.montarLaudoCompleto(new LaudoTextoBuilder(), "João", "Dra. Ana",
                "Gripe", "Repouso e hidratação");

        assertEquals("LAUDO MÉDICO\n"
                + "Paciente: João | Médico: Dra. Ana\n"
                + "Diagnóstico: Gripe\n"
                + "Prescrição: Repouso e hidratação\n"
                + "-- Documento emitido pelo hospital --", laudo.getConteudo());
    }

    @Test
    void deveMontarLaudoCompletoEmHtml() {
        Laudo laudo = diretor.montarLaudoCompleto(new LaudoHtmlBuilder(), "João", "Dra. Ana",
                "Gripe", "Repouso e hidratação");

        assertEquals("<h1>Laudo Médico</h1>\n"
                + "<p><b>Paciente:</b> João | <b>Médico:</b> Dra. Ana</p>\n"
                + "<p><b>Diagnóstico:</b> Gripe</p>\n"
                + "<p><b>Prescrição:</b> Repouso e hidratação</p>\n"
                + "<hr/><small>Documento emitido pelo hospital</small>", laudo.getConteudo());
    }

    @Test
    void deveMontarLaudoResumidoSemPrescricaoNemRodape() {
        Laudo laudo = diretor.montarLaudoResumido(new LaudoTextoBuilder(), "João", "Dra. Ana", "Gripe");

        assertEquals("LAUDO MÉDICO\n"
                + "Paciente: João | Médico: Dra. Ana\n"
                + "Diagnóstico: Gripe", laudo.getConteudo());
    }

    @Test
    void mesmoProcessoDeConstrucaoDeveGerarRepresentacoesDiferentes() {
        Laudo texto = diretor.montarLaudoResumido(new LaudoTextoBuilder(), "João", "Dra. Ana", "Gripe");
        Laudo html = diretor.montarLaudoResumido(new LaudoHtmlBuilder(), "João", "Dra. Ana", "Gripe");

        assertNotEquals(texto.getConteudo(), html.getConteudo());
        assertTrue(texto.getConteudo().contains("Diagnóstico: Gripe"));
        assertTrue(html.getConteudo().contains("<p><b>Diagnóstico:</b> Gripe</p>"));
    }

    @Test
    void oConstrutorDeveSerReutilizavelSemAcumularLaudosAnteriores() {
        ConstrutorLaudo construtor = new LaudoTextoBuilder();

        Laudo primeiro = diretor.montarLaudoResumido(construtor, "João", "Dra. Ana", "Gripe");
        Laudo segundo = diretor.montarLaudoResumido(construtor, "Maria", "Dr. Paulo", "Alergia");

        assertTrue(primeiro.getConteudo().contains("João"));
        assertFalse(segundo.getConteudo().contains("João"));
        assertTrue(segundo.getConteudo().contains("Maria"));
    }

    @Test
    void deveMontarOLaudoPassoAPassoSemODiretor() {
        ConstrutorLaudo construtor = new LaudoTextoBuilder();
        construtor.construirCabecalho("João", "Dra. Ana");
        construtor.construirRodape();

        assertEquals("LAUDO MÉDICO\n"
                + "Paciente: João | Médico: Dra. Ana\n"
                + "-- Documento emitido pelo hospital --", construtor.obterLaudo().getConteudo());
    }
}
