package padroesprojeto.builder.hospital;

public interface ConstrutorLaudo {

    void construirCabecalho(String paciente, String medico);

    void construirDiagnostico(String diagnostico);

    void construirPrescricao(String prescricao);

    void construirRodape();

    Laudo obterLaudo();
}
