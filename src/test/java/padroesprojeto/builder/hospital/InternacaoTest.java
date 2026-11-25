package padroesprojeto.builder.hospital;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternacaoTest {

    @Test
    void deveCriarInternacaoSoComOCampoObrigatorio() {
        Internacao internacao = new Internacao.Builder("João").build();

        assertEquals("Internacao{paciente='João', leito='A definir', medico='A definir', "
                + "convenio='Particular', urgente=false}", internacao.resumo());
    }

    @Test
    void deveCriarInternacaoComTodosOsCamposEncadeandoAsChamadas() {
        Internacao internacao = new Internacao.Builder("João")
                .leito("UTI-3")
                .medico("Dra. Ana")
                .convenio("Unimed")
                .urgente(true)
                .build();

        assertEquals("Internacao{paciente='João', leito='UTI-3', medico='Dra. Ana', "
                + "convenio='Unimed', urgente=true}", internacao.resumo());
        assertTrue(internacao.isUrgente());
    }

    @Test
    void deveAceitarSoAlgunsCamposOpcionaisEmQualquerOrdem() {
        Internacao internacao = new Internacao.Builder("Maria").convenio("Amil").leito("Quarto 12").build();

        assertEquals("Quarto 12", internacao.getLeito());
        assertEquals("Amil", internacao.getConvenio());
        assertEquals("A definir", internacao.getMedico());
    }

    @Test
    void naoDeveCriarInternacaoSemPaciente() {
        try {
            new Internacao.Builder("  ").build();
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Paciente é obrigatório", e.getMessage());
        }
    }

    @Test
    void naoDeveCriarInternacaoComPacienteNulo() {
        assertThrows(IllegalStateException.class, () -> new Internacao.Builder(null).build());
    }

    @Test
    void alterarOBuilderDepoisDoBuildNaoDeveAfetarOObjetoJaCriado() {
        Internacao.Builder builder = new Internacao.Builder("João").leito("Quarto 1");
        Internacao primeira = builder.build();

        builder.leito("Quarto 2");
        Internacao segunda = builder.build();

        assertEquals("Quarto 1", primeira.getLeito());
        assertEquals("Quarto 2", segunda.getLeito());
    }
}
