package padroesprojeto.builder.hospital;

import java.util.ArrayList;
import java.util.List;

public class LaudoTextoBuilder implements ConstrutorLaudo {

    private List<String> partes = new ArrayList<>();

    @Override
    public void construirCabecalho(String paciente, String medico) {
        partes.add("LAUDO MÉDICO");
        partes.add("Paciente: " + paciente + " | Médico: " + medico);
    }

    @Override
    public void construirDiagnostico(String diagnostico) {
        partes.add("Diagnóstico: " + diagnostico);
    }

    @Override
    public void construirPrescricao(String prescricao) {
        partes.add("Prescrição: " + prescricao);
    }

    @Override
    public void construirRodape() {
        partes.add("-- Documento emitido pelo hospital --");
    }

    @Override
    public Laudo obterLaudo() {
        Laudo laudo = new Laudo(String.join("\n", partes));
        partes = new ArrayList<>();
        return laudo;
    }
}
