package padroesprojeto.builder.hospital;

public class DiretorLaudo {

    public Laudo montarLaudoCompleto(ConstrutorLaudo construtor, String paciente, String medico,
                                     String diagnostico, String prescricao) {
        construtor.construirCabecalho(paciente, medico);
        construtor.construirDiagnostico(diagnostico);
        construtor.construirPrescricao(prescricao);
        construtor.construirRodape();
        return construtor.obterLaudo();
    }

    public Laudo montarLaudoResumido(ConstrutorLaudo construtor, String paciente, String medico,
                                     String diagnostico) {
        construtor.construirCabecalho(paciente, medico);
        construtor.construirDiagnostico(diagnostico);
        return construtor.obterLaudo();
    }
}
